package controller.parser.adminparser;


import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.DishRequest;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.database.worldonlinedb.dishes.DishCategoryEntity;
import model.database.worldonlinedb.dishes.DishEntity;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.logger.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDishesParser {
    private ArrayList<DishEntity> dishList;
    private Long priceID;
    private PriceEntity price;
    private Long categoryID;
    private DishCategoryEntity category;
    private HashMap<Integer, DishEntity> dishEntityHashMap;
    private ArrayList<TextEntity> textEntities;
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddDishesParser.class);

    public AddDishesParser(HttpServletRequest request) throws ServletException {
        try {
            dishEntityHashMap = new HashMap<>();
            dishList = new ArrayList<>();
            textEntities = new ArrayList<>();
            Map<String, String[]> textMap = request.getParameterMap();
            for (String name : textMap.keySet()) {
                if (name.equals("priceID")) {
                    try {
                        priceID = Long.parseLong(textMap.get(name)[0]);
                        price = DishRequest.getPrice(priceID);
                        if (price == null) {
                            throw new ServletException("incorrect price ID");
                        }
                    } catch (ServletException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new ServletException("incorrect price ID");
                    }
                }
                if (name.contains("dishName")) {
                    parseName(name, textMap.get(name)[0]);
                }
                if (name.contains("dishCoast")) {
                    parseCoast(name, textMap.get(name)[0]);
                }
                if (name.contains("categorySelect")) {
                    try {
                        categoryID = Long.parseLong(textMap.get(name)[0]);
                        category = DishRequest.getCategory(categoryID);
                        if (category == null) {
                            throw new ServletException("incorrect category ID");
                        }
                    } catch (ServletException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new ServletException("incorrect category ID");
                    }
                }
            }
            if (category == null) {
                throw new ServletException("category is empty");
            }
            if (price == null) {
                throw new ServletException("price is empty");
            }
            for (DishEntity dishEntity : dishEntityHashMap.values()) {
                if (dishEntity.getDishName() != null) {
                    dishEntity.setPrice(price);
                    dishEntity.setDishCategory(category);
                    dishList.add(dishEntity);
                }
            }
            if (dishList.isEmpty()) {
                throw new ServletException("all names are empty");
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            loggerFactory.error(e);
            throw new ServletException(e);
        }
    }

    private void parseCoast(String name, String value) throws ServletException {
        if (value != null && !value.replaceAll(" ", "").isEmpty()) {
            String idString = name.replaceFirst("dishCoast", "");
            int id;
            try {
                id = Integer.parseInt(idString);
            } catch (Exception e) {
                throw new ServletException("incorect id on field " + name + " (" + value + ")");
            }
            DishEntity dishEntity;
            if (!dishEntityHashMap.containsKey(id)) {
                dishEntity = new DishEntity();
                dishEntityHashMap.put(id, dishEntity);
            } else {
                dishEntity = dishEntityHashMap.get(id);
            }
            try {
                Double cost = Double.parseDouble(value);
                dishEntity.setCost(cost);
            } catch (Exception e) {
                throw new ServletException("cost is incorrect");
            }
        }
    }


    private void parseName(String name, String value) throws ServletException {
        if (value != null && !value.replaceAll(" ", "").isEmpty()) {
            String nameWithoutPart = name.replaceFirst("dishName", "");
            for (LanguageType languageType : LanguageType.values()) {
                if (nameWithoutPart.contains(languageType.toString())) {
                    String idString = nameWithoutPart.replaceFirst(languageType.toString(), "");
                    int id;
                    try {
                        id = Integer.parseInt(idString);
                    } catch (Exception e) {
                        throw new ServletException("incorect id on field " + name + " (" + value + ")");
                    }
                    DishEntity dishEntity;
                    if (!dishEntityHashMap.containsKey(id)) {
                        dishEntity = new DishEntity();
                        dishEntity.setDishName(new TextGroupEntity());
                        dishEntityHashMap.put(id, dishEntity);
                    } else {
                        dishEntity = dishEntityHashMap.get(id);
                        if (dishEntity.getDishName() == null) {
                            dishEntity.setDishName(new TextGroupEntity());
                        }
                    }
                    if (languageType == LanguageType.English) {
                        dishEntity.getDishName().setTextGroupName("dish_" + value.replaceAll(" ", "_"));
                    }
                    TextEntity textEntity = new TextEntity(languageType, value, dishEntity.getDishName());
                    textEntities.add(textEntity);
                }
            }
        }
    }

    public ArrayList<DishEntity> getDishList() {
        return dishList;
    }


    public ArrayList<TextEntity> getTextEntities() {
        return textEntities;
    }


    public Long getPriceID() {
        return priceID;
    }


    public String toString() {
        String res = "";
        for (DishEntity dishEntity : dishEntityHashMap.values()) {
            if (dishEntity == null) {
                res += "null";
            } else {
                if (dishEntity.getDishName() == null) {
                    res += "(" + "null" + " " + dishEntity.getCost() + ")";
                } else {
                    res += "(" + dishEntity.getDishName().getTextGroupName() + " " + dishEntity.getCost() + ")";
                }
            }
            res += ", ";
        }
        return res;
    }
}
