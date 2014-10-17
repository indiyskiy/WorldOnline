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
import model.database.worldonlinedb.dishes.CardPriceLinkEntity;
import model.database.worldonlinedb.dishes.DishCategoryEntity;
import model.database.worldonlinedb.dishes.DishEntity;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.DishAdditionalInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class DishParser {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, DishParser.class);
    public static ArrayList<DishAdditionalInfo> menuCardList = parseDishAdditionalInfo(ServerConsts.root + "addData/additionalDishInfo.txt");
    private static HashMap<String, DishCategoryEntity> dishCategoryMap = new HashMap<>();

    private static ArrayList<DishAdditionalInfo> parseDishAdditionalInfo(String root) {
        ArrayList<DishAdditionalInfo> dishAdditionalInfoList = new ArrayList<>();
        InputStream fis = null;
        BufferedReader br = null;
        String line;
        File file = new File(root);
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                try {
                    line = line.replaceAll("﻿", "");
                    String[] elements = line.split("=\\^__\\^=");
                    if (elements.length > 0) {
                        Integer parentID = Integer.parseInt(elements[0]);
                        Integer order = Integer.parseInt(elements[1]);
                        String nameEng = elements[2];
                        String nameRus = elements[3];
                        String categoryNameEng = elements[4];
                        String categoryNameRus = elements[5];
                        if ((nameEng == null || nameEng.isEmpty()) && (nameRus == null || nameRus.isEmpty())) {
                            continue;
                        }
                        DishAdditionalInfo dishAdditionalInfo = new DishAdditionalInfo();
                        if (!elements[6].isEmpty()) {
                            Integer catID = Integer.parseInt(elements[6]);
                            dishAdditionalInfo.setCatID(catID);
                        }
                        dishAdditionalInfo.setCategoryNameEng(categoryNameEng);
                        dishAdditionalInfo.setCategoryNameRus(categoryNameRus);
                        String price = elements[7];
                        dishAdditionalInfo.setNameEng(nameEng);
                        dishAdditionalInfo.setNameRus(nameRus);
                        dishAdditionalInfo.setOrder(order);
                        dishAdditionalInfo.setParentID(parentID);
                        Double.parseDouble(price);
                        dishAdditionalInfo.setPrice(price);
                        dishAdditionalInfoList.add(dishAdditionalInfo);
                    }
                } catch (Exception e) {
                    loggerFactory.error(e);
                    loggerFactory.error("error at parse line " + line);
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
        return dishAdditionalInfoList;
    }

    public void parseDishes(HashMap<Long, CardEntity> cardEntityHashMap) {
        HashMap<Integer, PriceEntity> priceEntityHashMap = new HashMap<>();

        for (DishAdditionalInfo dish : menuCardList) {
            try {
                loggerFactory.debug("parsing dish " + dish.getNameRus());
                PriceEntity priceEntity;
                if (priceEntityHashMap.containsKey(dish.getParentID())) {
                    priceEntity = priceEntityHashMap.get(dish.getParentID());
                } else {
                    TextGroupEntity nameGroup = new TextGroupEntity("MenuPriceName");
                    TextEntity menuEnName = new TextEntity(LanguageType.English, "Menu", nameGroup);
                    TextEntity menuRuName = new TextEntity(LanguageType.Russian, "Меню", nameGroup);
                    TextRequest.addText(menuEnName);
                    TextRequest.addText(menuRuName);

                    priceEntity = new PriceEntity();
                    priceEntity.setPriceName(nameGroup);
                    CardEntity cardEntity = cardEntityHashMap.get(Long.valueOf(dish.getParentID()));
                    DishRequest.addPrice(priceEntity);
                    CardPriceLinkEntity cardPriceLinkEntity = new CardPriceLinkEntity();
                    cardPriceLinkEntity.setPrice(priceEntity);
                    cardPriceLinkEntity.setCard(cardEntity);
                    DishRequest.addCardPriceLink(cardPriceLinkEntity);
                    priceEntityHashMap.put(dish.getParentID(), priceEntity);
                }

                DishEntity dishEntity = new DishEntity();
                boolean coastIsValid = ParameterValidator.isValidParameter(dish.getPrice(), DataType.DoubleType);
                if (coastIsValid) {
                    dishEntity.setCost(Double.parseDouble(dish.getPrice()));
                } else {
                    loggerFactory.error(dish.getParentID() + " " + dish.getNameRus() + " illegal cost " + dish.getPrice());
                }
                dishEntity.setPrice(priceEntity);
                setCategory(dishEntity, dish.getCategoryNameRus(), dish.getCategoryNameEng(), dish.getParentID(), dish.getOrder());
                TextGroupEntity dishNameGroup = new TextGroupEntity("DishName" + dish.getNameEng());
                if (dish.getNameRus() != null && !dish.getNameRus().isEmpty()) {
                    TextEntity dishNameRu = new TextEntity(LanguageType.Russian, dish.getNameRus(), dishNameGroup);
                    TextRequest.addText(dishNameRu);
                }
                if (dish.getNameEng() != null && !dish.getNameEng().isEmpty()) {
                    TextEntity dishNameEn = new TextEntity(LanguageType.English, dish.getNameEng(), dishNameGroup);
                    TextRequest.addText(dishNameEn);
                }
                dishEntity.setDishName(dishNameGroup);

                if (!DishRequest.isDishExist(dish.getCategoryNameRus(), dish.getCategoryNameEng(), dishEntity.getCost(), dishEntity.getPrice().getPriceID())) {
                    DishRequest.addDish(dishEntity);
                }
            } catch (Exception e) {
                loggerFactory.error("error on dish " + dish.getNameRus() + " at card " + dish.getParentID());
                loggerFactory.error(e);
            } catch (Throwable t) {
                loggerFactory.error("error on dish " + dish.getNameRus() + " at card " + dish.getParentID());
                loggerFactory.error(t.toString() + " " + t.getMessage());
            }
        }
    }

    private void setCategory(DishEntity dishEntity, String nameRu, String nameEn, Integer parentID, Integer order) {
        try {
            if (nameRu != null && parentID != null) {
                String key = nameRu + parentID;

                DishCategoryEntity dishCategoryEntity;
                if (dishCategoryMap.containsKey(key)) {
                    dishCategoryEntity = dishCategoryMap.get(key);
                } else {
                    TextGroupEntity textGroupEntity = new TextGroupEntity("DishCategoryName" + nameEn);
                    TextEntity textEntityRu = new TextEntity(LanguageType.Russian, nameRu, textGroupEntity);
                    TextEntity textEntityEn = new TextEntity(LanguageType.English, nameEn, textGroupEntity);
                    TextRequest.addText(textEntityRu);
                    TextRequest.addText(textEntityEn);
                    dishCategoryEntity = new DishCategoryEntity();
                    dishCategoryEntity.setName(textGroupEntity);
//                    dishCategoryEntity.setName(textGroupEntity);
                    dishCategoryEntity.setPosition(order);
                    DishRequest.addDishCategory(dishCategoryEntity);
                    dishCategoryMap.put(key, dishCategoryEntity);
                }
                if (dishCategoryEntity == null) {
                    loggerFactory.error(nameRu + " is null");
                }
                dishEntity.setDishCategory(dishCategoryEntity);
            }
        } catch (Exception e) {
            loggerFactory.error("error on card " + parentID + " on category " + nameRu);
            loggerFactory.error(e);
        }
    }


    public void saveDishes(HashMap<Long, CardEntity> cardEntityHashMap) {
        try {
            parseDishes(cardEntityHashMap);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

}
