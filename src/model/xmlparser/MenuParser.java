package model.xmlparser;

import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MenuImageType;
import model.constants.databaseenumeration.MenuType;
import model.database.requests.MenuRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.ImageEntity;
import model.database.worldonlinedb.MenuEntity;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.mainmenudata.MainMenuData;
import model.xmlparser.xmlview.mainmenudata.Submenu;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class MenuParser {
    private HashMap<Long, Long> downloadedMenuHash = new HashMap<Long, Long>();
    private HashMap<String, MenuEntity> menuEntityHashMap = new HashMap<String, MenuEntity>();
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, MenuParser.class);

    public static void main(String[] args) {
        MenuParser menuParser = new MenuParser();
        menuParser.saveMenu();
        MenuCardLinkParser menuCardLinkParser = new MenuCardLinkParser();
        menuCardLinkParser.parseMenuCardLink();
    }

    public void saveMenu() {
        MainMenuData mainMenuData = getMainMenuData(ServerConsts.root + "MainMenuData.xml");
        loadHardcodedMenues();
        saveMainMenu(mainMenuData);
        saveSubmenu(mainMenuData);
    }

    private void saveSubmenu(MainMenuData mainMenuData) {
        List<Submenu> submenus = mainMenuData.getSubmenus();
        List<Submenu> secondLevel = new ArrayList<Submenu>();
        for (Submenu submenu : submenus) {
            if (submenu.mainMenuID != null && submenu.mainMenuID.contains("_")) {
                secondLevel.add(submenu);
            }
        }
        addMenusToDB(secondLevel);
        for (Submenu submenu : secondLevel) {
            String[] array = submenu.mainMenuID.split("_");
            Long rootMenuID = Long.parseLong(array[0]);
            Long parentMenuID = Long.parseLong(array[1]);
            Submenu parent = findSubmenu(parentMenuID, rootMenuID, submenus);
            parentMenuID = Long.parseLong(parent.id);
            MenuEntity parentMenu = MenuRequest.getMenu(downloadedMenuHash.get(parentMenuID));
            MenuEntity menu = MenuRequest.getMenu(downloadedMenuHash.get(Long.parseLong(submenu.id)));
            menu.setParentMenu(parentMenu);
            if (parentMenu != null) {
                MenuRequest.setParent(menu);
            }
        }
    }

    public MainMenuData getMainMenuData(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(MainMenuData.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public void saveMainMenu(MainMenuData mainMenuData) {
        List<Submenu> submenus = mainMenuData.getSubmenus();
        List<Submenu> firstLevel = new ArrayList<Submenu>();
        for (Submenu submenu : submenus) {
            if (submenu.mainMenuID != null && !submenu.mainMenuID.contains("_")) {
                firstLevel.add(submenu);
            }
        }
        count(firstLevel);
        addMenusToDB(firstLevel);
        for (Submenu submenu : firstLevel) {
            Long parentMenuID;
            Long menuID = Long.parseLong(submenu.id);
            if (submenu.mainMenuID != null) {
                parentMenuID = Long.parseLong(submenu.mainMenuID);
                MenuEntity parentMenu = MenuRequest.getMenu(downloadedMenuHash.get(parentMenuID));
                MenuEntity menu = MenuRequest.getMenu(downloadedMenuHash.get(menuID));
                menu.setParentMenu(parentMenu);
                if (parentMenu != null) {
                    MenuRequest.setParent(menu);
                }
            }
        }
    }

    private void count(List<Submenu> firstLevel) {
        HashMap<Long, Long> counter = new HashMap<Long, Long>();
        for (Submenu submenu : firstLevel) {
            if (!counter.containsKey(Long.parseLong(submenu.mainMenuID))) {
                counter.put(Long.parseLong(submenu.mainMenuID), 1L);
                submenu.number = 1L;
            } else {
                long count = counter.get(Long.parseLong(submenu.mainMenuID)) + 1;
                counter.remove(Long.parseLong(submenu.mainMenuID));
                counter.put(Long.parseLong(submenu.mainMenuID), count);
                submenu.number = count;
            }
        }
    }

    private Submenu findSubmenu(Long number, Long parentID, List<Submenu> submenus) {
        for (Submenu submenu : submenus) {
            if (submenu.number != null) {
                try {
                    if (submenu.number.equals(number) && !submenu.mainMenuID.contains("_") && parentID == Long.parseLong(submenu.mainMenuID)) {
                        return submenu;
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println(submenu);
                    System.out.println(submenu.nameRU);
                    System.out.println(submenu.number);
                    System.out.println(submenu.mainMenuID);
                    throw e;
                }
            }
        }
        return null;
    }

    private void addMenusToDB(List<Submenu> submenus) {
        for (Submenu submenu : submenus) {
            MenuEntity mainMenu = null;
            Long menuID = Long.parseLong(submenu.id);
            TextGroupEntity textGroupEntity = new TextGroupEntity(submenu.nameEN);
            TextEntity ruText = new TextEntity(LanguageType.Russian, submenu.nameRU, textGroupEntity);
            TextEntity enText = new TextEntity(LanguageType.English, submenu.nameEN, textGroupEntity);
            TextRequest.addText(ruText);
            TextRequest.addText(enText);
            ImageEntity menuImage = getMenuImage(submenu.image, MenuImageType.Simple);
            ImageEntity pushedMenuImage = getMenuImage(submenu.image, MenuImageType.Pushed);
            MenuType menuType = null;
            MenuEntity menuEntity = new MenuEntity(mainMenu, textGroupEntity, menuImage, pushedMenuImage, menuType);
            menuEntity.setNumber(Integer.parseInt(submenu.orderID));
            if (menuEntity.getNumber() == null) {
                System.out.println("NULL NUMBER!!! " + submenu.nameRU + " " + submenu.orderID);
            }
            MenuRequest.addMenu(menuEntity);
            downloadedMenuHash.put(menuID, menuEntity.getMenuID());
            put(submenu.id, menuEntity);
//            System.out.println(menuID + " " + menuEntity.getMenuID());
        }
    }

    private ImageEntity getMenuImage(String image, MenuImageType simple) {
//        System.out.println(image);
        return null;
    }

    private void put(String menuID, MenuEntity menuEntity) {
        menuEntityHashMap.put(menuID, menuEntity);
    }

    private void loadHardcodedMenues() {
        List<Submenu> menu = new ArrayList<Submenu>();
        Submenu overview = new Submenu("0", null, "Обзор", "Overview", "icon-information.png", "1", null);
        Submenu places = new Submenu("1", null, "Места", "Places", "icon-places.png", "2", null);
        Submenu tours = new Submenu("2", null, "Маршруты", "Tours", "icon-tracks.png", "3", null);
        Submenu hotels = new Submenu("3", null, "Размещение", "Hotels", "icon-hotels.png", "4", null);
        Submenu food = new Submenu("4", null, "Еда", "Food", "icon-food.png", "5", null);
        Submenu nightlife = new Submenu("5", null, "Развлечения", "Nightlife", "icon-nightlife.png", "6", null);
        Submenu shopping = new Submenu("6", null, "Шоппинг", "Shopping", "icon-o_shopping.png", "7", null);
        Submenu directory = new Submenu("7", null, "Справочник", "Directory", "icon-o_gorode.png", "8", null);
        Submenu photo = new Submenu("8", null, "Фото", "Photo", "icon-photos.png", "9", null);
        menu.add(photo);
        menu.add(directory);
        menu.add(overview);
        menu.add(nightlife);
        menu.add(food);
        menu.add(hotels);
        menu.add(shopping);
        menu.add(tours);
        menu.add(places);
        addMenusToDB(menu);
    }

    public HashMap<String, MenuEntity> getMenuEntityHashMap() {
        return menuEntityHashMap;
    }
}
