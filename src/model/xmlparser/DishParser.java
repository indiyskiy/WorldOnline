package model.xmlparser;

import helper.ParameterValidator;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.DataType;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.DishRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.database.worldonlinedb.dishes.*;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.DishAdditionalInfo;
import model.xmlparser.xmlview.menu.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;

public class DishParser {
    private static final String menuXmlRoute = ServerConsts.root + "MenuXML.xml";
    private static final String tagsXmlRoute = ServerConsts.root + "TagsXML.xml";
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, DishParser.class);
    public static HashMap<String, DishAdditionalInfo> menuCardMap = parseDishAdditionalInfo(ServerConsts.root + "addData/additionalDishInfo.txt");
    private static HashMap<String, DishCategoryEntity> dishCategoryMap = new HashMap<>();

    private static HashMap<String, DishAdditionalInfo> parseDishAdditionalInfo(String root) {
        HashMap<String, DishAdditionalInfo> dishAdditionalInfoHashMap = new HashMap<>();
        InputStream fis = null;
        BufferedReader br = null;
        String line;
        File file = new File(root);
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                String[] elements = line.split("=\\^__\\^=");
                if (elements.length > 0) {
                    Integer parentID = Integer.parseInt(elements[0]);
                    Integer order = Integer.parseInt(elements[1]);
                    String nameEng = elements[2];
                    String nameRus = elements[3];
                    String categoryNameEng = elements[4];
                    String categoryNameRus = elements[5];
                    DishAdditionalInfo dishAdditionalInfo = new DishAdditionalInfo();
                    if (!elements[6].isEmpty()) {
                        Integer catID = Integer.parseInt(elements[6]);
                        dishAdditionalInfo.setCategoryNameEng(categoryNameEng);
                        dishAdditionalInfo.setCategoryNameRus(categoryNameRus);
                        dishAdditionalInfo.setCatID(catID);
                    }
                    dishAdditionalInfo.setNameEng(nameEng);
                    dishAdditionalInfo.setNameRus(nameRus);
                    dishAdditionalInfo.setOrder(order);
                    dishAdditionalInfo.setParentID(parentID);
                    dishAdditionalInfoHashMap.put(nameRus, dishAdditionalInfo);
                }
            }
            br.close();
        } catch (IOException e) {
            loggerFactory.error(e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                loggerFactory.error(e);
            }
        }
        return dishAdditionalInfoHashMap;
    }

    public void parseDishes(HashMap<Long, CardEntity> cardEntityHashMap, HashMap<Integer, DishTagEntity> dishTagEntityHashMap) {
        MenuXML menuXML = new SimpleXmlHelper().getMenuXML(menuXmlRoute);
        HashMap<Integer, PriceEntity> priceEntityHashMap = new HashMap<>();
        TextGroupEntity nameGroup = new TextGroupEntity("MenuPriceName");
        TextEntity menuEnName = new TextEntity(LanguageType.English, "Menu", nameGroup);
        TextEntity menuRuName = new TextEntity(LanguageType.Russian, "Меню", nameGroup);
        TextRequest.addText(menuEnName);
        TextRequest.addText(menuRuName);
        for (Dish dish : menuXML.dishes) {
            try {
                loggerFactory.debug("parsing dish " + dish.nameRu);
                PriceEntity priceEntity;
                if (priceEntityHashMap.containsKey(dish.restID)) {
                    priceEntity = priceEntityHashMap.get(dish.restID);
                } else {
                    priceEntity = new PriceEntity();
                    priceEntity.setPriceName(nameGroup);
                    CardEntity cardEntity = cardEntityHashMap.get(Long.valueOf(dish.restID));
                    DishRequest.addPrice(priceEntity);
                    CardPriceLinkEntity cardPriceLinkEntity = new CardPriceLinkEntity();
                    cardPriceLinkEntity.setPrice(priceEntity);
                    cardPriceLinkEntity.setCard(cardEntity);
                    DishRequest.addCardPriceLink(cardPriceLinkEntity);
                    priceEntityHashMap.put(dish.restID, priceEntity);
                }
                TextGroupEntity textGroupEntity = new TextGroupEntity("dish" + dish.nameEn);
                if (dish.nameEn != null && !dish.nameEn.isEmpty()) {
                    TextEntity textEntityEn = new TextEntity(LanguageType.Russian, dish.nameRu, textGroupEntity);
                    TextRequest.addText(textEntityEn);
                }
                if (dish.nameRu != null && !dish.nameRu.isEmpty()) {
                    TextEntity textEntityRu = new TextEntity(LanguageType.English, dish.nameEn, textGroupEntity);
                    TextRequest.addText(textEntityRu);
                }
                DishEntity dishEntity = new DishEntity();
                String coastString = ParameterValidator.isValidParameter(dish.price, DataType.DoubleType);
                if (coastString != null) {
                    dishEntity.setCost(Double.parseDouble(coastString));
                } else {
                    loggerFactory.error(dish.restID + " " + dish.nameRu + " illegal cost " + dish.price);
                }
                dishEntity.setPrice(priceEntity);
                setCategory(dishEntity, dish.nameRu);
                TextGroupEntity dishNameGroup = new TextGroupEntity("DishName" + dish.nameEn);
                if (dish.nameRu != null && !dish.nameRu.isEmpty()) {
                    TextEntity dishNameRu = new TextEntity(LanguageType.Russian, dish.nameRu, dishNameGroup);
                    TextRequest.addText(dishNameRu);
                }
                if (dish.nameEn != null && !dish.nameEn.isEmpty()) {
                    TextEntity dishNameEn = new TextEntity(LanguageType.English, dish.nameEn, dishNameGroup);
                    TextRequest.addText(dishNameEn);
                }
                dishEntity.setDishName(dishNameGroup);
                if (dishEntity.getDishCategory() != null) {
                    loggerFactory.debug("set " + dish.nameRu + " " + dishEntity.getDishCategory().getName().getTextGroupName());
                }
                DishRequest.addDish(dishEntity);
                for (Tag tag : dish.tags) {
                    DishTagEntity dishTagEntity = dishTagEntityHashMap.get(tag.tagID);
                    DishTagDishLinkEntity dishTagDishLinkEntity = new DishTagDishLinkEntity();
                    dishTagDishLinkEntity.setDish(dishEntity);
                    dishTagDishLinkEntity.setDishTag(dishTagEntity);
                    DishRequest.addDishTagDishLink(dishTagDishLinkEntity);
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    private void setCategory(DishEntity dishEntity, String nameRu) {
        loggerFactory.debug("setCategory of " + nameRu);
        if (menuCardMap.containsKey(nameRu)) {
            loggerFactory.debug("nameru " + nameRu + " was found");
            DishAdditionalInfo dishAdditionalInfo = menuCardMap.get(nameRu);
            if (dishAdditionalInfo.getCatID() != null) {
                loggerFactory.debug("getCatID not null");
                String catNameRu = dishAdditionalInfo.getCategoryNameRus();
//                Integer categoryID = dishAdditionalInfo.getCatID();
                DishCategoryEntity dishCategoryEntity;
                if (dishCategoryMap.containsKey(catNameRu)) {
                    loggerFactory.debug(catNameRu + " categoryID found");
                    dishCategoryEntity = dishCategoryMap.get(catNameRu);
                } else {
                    loggerFactory.debug(catNameRu + " categoryID not found");
                    TextGroupEntity textGroupEntity = new TextGroupEntity("DishCategoryName" + dishAdditionalInfo.getCategoryNameEng());
                    TextEntity textEntityRu = new TextEntity(LanguageType.Russian, dishAdditionalInfo.getCategoryNameRus(), textGroupEntity);
                    TextEntity textEntityEn = new TextEntity(LanguageType.English, dishAdditionalInfo.getCategoryNameEng(), textGroupEntity);
                    TextRequest.addText(textEntityRu);
                    TextRequest.addText(textEntityEn);
                    dishCategoryEntity = new DishCategoryEntity();
                    dishCategoryEntity.setName(textGroupEntity);
                    dishCategoryEntity.setName(textGroupEntity);
                    dishCategoryEntity.setPosition(dishAdditionalInfo.getOrder());
                    DishRequest.addDishCategory(dishCategoryEntity);
                    dishCategoryMap.put(catNameRu, dishCategoryEntity);
                    loggerFactory.debug("added category " + catNameRu + " (" + textEntityRu.getText() + ") " + dishCategoryMap.get(catNameRu));
                }
                dishEntity.setDishCategory(dishCategoryEntity);
            }
        }
    }


    public void saveDishes(HashMap<Long, CardEntity> cardEntityHashMap) {
        try {
            HashMap<Integer, DishTagEntity> dishTagEntityHashMap = saveTags();
            parseDishes(cardEntityHashMap, dishTagEntityHashMap);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private HashMap<Integer, DishTagEntity> saveTags() {
        HashMap<Integer, DishTagEntity> tagMap = new HashMap<>();
        TagsXML tagsXML = new SimpleXmlHelper().getTagsXML(tagsXmlRoute);
        for (CompleteTag completeTag : tagsXML.tags) {
            TextGroupEntity textGroup = new TextGroupEntity("dishTag" + completeTag.nameEn);
            if (completeTag.nameRu != null && !completeTag.nameRu.isEmpty()) {
                TextEntity ruName = new TextEntity(LanguageType.Russian, completeTag.nameRu, textGroup);
                TextRequest.addText(ruName);
            }
            if (completeTag.nameEn != null && !completeTag.nameEn.isEmpty()) {
                TextEntity enName = new TextEntity(LanguageType.English, completeTag.nameEn, textGroup);
                TextRequest.addText(enName);
            }
            DishTagEntity dishTagEntity = new DishTagEntity();
            dishTagEntity.setName(textGroup);
            DishRequest.addDishTag(dishTagEntity);
            tagMap.put(completeTag.id, dishTagEntity);
        }
        return tagMap;
    }

}
