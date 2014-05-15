package model.xmlparser;

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
import java.util.HashMap;
import java.util.List;

public class MenuParser {
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
        loggerFactory.debug("saveSubmenu");
        addMenusToDB(secondLevel, MenuType.StandardMenu);
        loggerFactory.debug("/saveSubmenu");
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
                    loggerFactory.debug("add to list " + submenu.nameRU + " " + submenu.nameEN);
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
                    MenuEntity menu = MenuRequest.getMenu(downloadedMenuHash.get(menuID));
                    menu.setParentMenu(parentMenu);
                    if (parentMenu != null) {
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

    private void addMenusToDB(List<Submenu> submenus, MenuType menuType) {
        for (Submenu submenu : submenus) {
            loggerFactory.debug("save " + submenu.nameEN + " " + submenu.nameRU);
            try {
                Long menuID = Long.parseLong(submenu.id);
                TextGroupEntity textGroupEntity = new TextGroupEntity(submenu.nameEN);
                TextRequest.addTextGroup(textGroupEntity);
                TextEntity ruText = new TextEntity(LanguageType.Russian, submenu.nameRU, textGroupEntity);
                TextEntity enText = new TextEntity(LanguageType.English, submenu.nameEN, textGroupEntity);
                TextRequest.addText(ruText);
                TextRequest.addText(enText);
                ImageEntity menuImage = getMenuImage(submenu.image);
                MenuEntity menuEntity = new MenuEntity(null, textGroupEntity, menuImage, menuType);
                menuEntity.setNumber(Integer.parseInt(submenu.orderID));
                if (menuEntity.getNumber() == null) {
                    loggerFactory.error("NULL NUMBER!!! " + submenu.nameRU + " " + submenu.orderID);
                }
                MenuRequest.addMenu(menuEntity);
                downloadedMenuHash.put(menuID, menuEntity.getMenuID());
                put(submenu.id, menuEntity);
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    private ImageEntity getMenuImage(String imageName) {
        try {
            if (imageName == null || imageName.isEmpty()) {
                return null;
            }
            String root = ServerConsts.oldIconsRoot + imageName;
            File imageFile = new File(root);
            if (!imageFile.exists()) {
                return null;
            }
            BufferedImage bimg = ImageIO.read(imageFile);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            long size = imageFile.length();
            String hash = Md5Hash.getMd5Hash(imageFile);
            ImageEntity imageEntity = ImageRequest.getImageByHash(hash);
            if (imageEntity == null) {
                ImageType imageType = ImageType.MenuIcon;
                GlobalXmlParser.saveFile(imageFile, imageName, ServerConsts.imageFolder + imageType);
                String path = ServerConsts.imageFolder + imageType.toString() + "/" + imageName;
                imageEntity = new ImageEntity(path, height, width, size, hash);
            }
            return imageEntity;
        } catch (Exception e) {
            loggerFactory.error(e);
        }
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
        addMenusToDB(menu, MenuType.RootMenu);
    }

    public HashMap<String, MenuEntity> getMenuEntityHashMap() {
        return menuEntityHashMap;
    }
}
