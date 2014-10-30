package controller.parser.adminparser;


import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.DishRequest;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.database.worldonlinedb.dishes.DishCategoryEntity;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.logger.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

public class AddCategoryParser {
    private Long priceID;
    private PriceEntity price;
    private ArrayList<TextEntity> textEntities;
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddDishesParser.class);
    DishCategoryEntity dishCategoryEntity = new DishCategoryEntity();

    public AddCategoryParser(HttpServletRequest request) throws ServletException {
        dishCategoryEntity.setName(new TextGroupEntity());
        try {
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
                if (name.contains("categoryName")) {
                    parseName(name, textMap.get(name)[0]);
                }
            }
            if (price == null) {
                throw new ServletException("price is empty");
            }
            dishCategoryEntity.setPrice(price);
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            loggerFactory.error(e);
            throw new ServletException(e);
        }
    }


    private void parseName(String name, String value) throws ServletException {
        if (value != null && !value.replaceAll(" ", "").isEmpty()) {
            String nameWithoutPart = name.replaceFirst("categoryName", "");
            for (LanguageType languageType : LanguageType.values()) {
                if (nameWithoutPart.contains(languageType.toString())) {
                    if (languageType == LanguageType.English) {
                        dishCategoryEntity.getName().setTextGroupName("dishCat_" + value.replaceAll(" ", "_"));
                    }
                    TextEntity textEntity = new TextEntity(languageType, value, dishCategoryEntity.getName());
                    textEntities.add(textEntity);
                    return;
                }
            }
        }
    }

    public void setPriceID(Long priceID) {
        this.priceID = priceID;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public void setTextEntities(ArrayList<TextEntity> textEntities) {
        this.textEntities = textEntities;
    }

    public DishCategoryEntity getDishCategoryEntity() {
        return dishCategoryEntity;
    }

    public void setDishCategoryEntity(DishCategoryEntity dishCategoryEntity) {
        this.dishCategoryEntity = dishCategoryEntity;
    }

    public ArrayList<TextEntity> getTextEntities() {
        return textEntities;
    }


    public Long getPriceID() {
        return priceID;
    }


}
