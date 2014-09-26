package model.xmlparser;

import helper.FileHelper;
import helper.ImageHelper;
import helper.Md5Hash;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.ImageType;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MenuType;
import model.database.requests.ImageRequest;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MenuParser {
    public static final String MENU_ICON = "MenuIcon";
    private HashMap<Long, Long> downloadedMenuHash = new HashMap<>();
    private HashMap<String, MenuEntity> menuEntityHashMap = new HashMap<String, MenuEntity>();
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, MenuParser.class);

    public void saveMenu() {
        try {
            MainMenuData mainMenuData = getMainMenuData(ServerConsts.root + "MainMenuData.xml");
            loadHardcodedMenues();
            saveMainMenu(mainMenuData);
            saveSubmenu(mainMenuData);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private void saveSubmenu(MainMenuData mainMenuData) {
        List<Submenu> submenus = mainMenuData.getSubmenus();
        List<Submenu> secondLevel = new ArrayList<Submenu>();
        for (Submenu submenu : submenus) {
            if (submenu.mainMenuID != null && submenu.mainMenuID.contains("_")) {
                secondLevel.add(submenu);
            }
        }
        addMenusToDB(secondLevel, MenuType.StandardMenu);
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
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public void saveMainMenu(MainMenuData mainMenuData) {
        try {
            List<Submenu> submenus = mainMenuData.getSubmenus();
            List<Submenu> firstLevel = new ArrayList<Submenu>();
            for (Submenu submenu : submenus) {
                if (submenu.mainMenuID != null && !submenu.mainMenuID.contains("_")) {
                    firstLevel.add(submenu);
                }
            }
            count(firstLevel);
            addMenusToDB(firstLevel, MenuType.StandardMenu);
            for (Submenu submenu : firstLevel) {
                Long parentMenuID;
                Long menuID = Long.parseLong(submenu.id);
                if (submenu.mainMenuID != null) {
                    parentMenuID = Long.parseLong(submenu.mainMenuID);
                    Long id = downloadedMenuHash.get(parentMenuID);
                    MenuEntity parentMenu = MenuRequest.getMenu(id);
                    if (parentMenu != null) {
                        MenuEntity menu = MenuRequest.getMenu(downloadedMenuHash.get(menuID));
                        menu.setParentMenu(parentMenu);
                        MenuRequest.setParent(menu);
                    }
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
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
                    String s = String.valueOf(submenu);
                    s += "\r\n" + submenu.nameRU;
                    s += "\r\n" + submenu.number;
                    s += "\r\n" + submenu.mainMenuID;
                    loggerFactory.error(e);
                    loggerFactory.error(s);
                    throw e;
                }
            }
        }
        return null;
    }

    private HashMap<String, MenuEntity> addMenusToDB(List<Submenu> submenus, MenuType menuType) {
        HashMap<String, MenuEntity> menuEntities = new HashMap<>();
        for (Submenu submenu : submenus) {
            try {
                Long menuID = Long.parseLong(submenu.id);
                TextGroupEntity textGroupEntity = new TextGroupEntity(submenu.nameEN);
                TextRequest.addTextGroup(textGroupEntity);
                TextEntity ruText = new TextEntity(LanguageType.Russian, submenu.nameRU, textGroupEntity);
                TextEntity enText = new TextEntity(LanguageType.English, submenu.nameEN, textGroupEntity);
                TextRequest.addText(ruText);
                TextRequest.addText(enText);
                ImageEntity menuImage = ImageHelper.saveImage(submenu.image, ImageType.MenuIcon);
                MenuEntity menuEntity = new MenuEntity(null, textGroupEntity, menuImage, menuType);
                menuEntity.setNumber(Integer.parseInt(submenu.orderID));
                if (menuEntity.getNumber() == null) {
                    loggerFactory.error("NULL NUMBER!!! " + submenu.nameRU + " " + submenu.orderID);
                }
                MenuRequest.addMenu(menuEntity);
                menuEntities.put(submenu.nameEN, menuEntity);
                downloadedMenuHash.put(menuID, menuEntity.getMenuID());
                put(submenu.id, menuEntity);
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return menuEntities;
    }


    private void put(String menuID, MenuEntity menuEntity) {
        menuEntityHashMap.put(menuID, menuEntity);
    }

    private void loadHardcodedMenues() {
        List<Submenu> main = new ArrayList<>();
        Submenu aboutCity = new Submenu("-3", null, "О городе", "About city", "icon-aboutCity.png", "1", null);
        Submenu mainPlaces = new Submenu("-2", null, "Места", "Places", "icon-places.png", "2", null);
        Submenu events = new Submenu("-1", null, "События", "Events", "icon-events.png", "3", null);
        main.add(aboutCity);
        main.add(mainPlaces);
        main.add(events);
        HashMap<String, MenuEntity> mainMenuEntities = addMenusToDB(main, MenuType.MainScreenMenu);

        MenuEntity placeMenuEntity = mainMenuEntities.get(mainPlaces.nameEN);


        List<Submenu> menu = new ArrayList<>();
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
        Collection<MenuEntity> menuEntities = addMenusToDB(menu, MenuType.RootMenu).values();
        for (MenuEntity menuEntity : menuEntities) {
            menuEntity.setParentMenu(placeMenuEntity);
            MenuRequest.setParent(menuEntity);
        }
    }

    public HashMap<String, MenuEntity> getMenuEntityHashMap() {
        return menuEntityHashMap;
    }
}
