package model.additionalentity.admin;


import model.additionalentity.SimpleCard;

import java.util.ArrayList;
import java.util.HashMap;

public class CompletePriceInfo {
    private Long priceID;
    private String name;
    private Long textGroupID;
    private ArrayList<SimpleCategory> simpleCategories = new ArrayList<>();
    private ArrayList<SimpleCard> cards = new ArrayList<>();

    public Long getPriceID() {
        return priceID;
    }

    public void setPriceID(Long priceID) {
        this.priceID = priceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public void setCategories(ArrayList<SimpleDish> simpleDishes) {
        HashMap<Long, SimpleCategory> simpleCategoryHashMap = new HashMap<>();
        SimpleCategory nullCategory = new SimpleCategory();
        nullCategory.setName("Без категории");
        simpleCategoryHashMap.put(null, nullCategory);
        simpleCategories.add(nullCategory);
        for (SimpleDish simpleDish : simpleDishes) {
            Long catID = simpleDish.getCategoryID();
            if (catID != null && catID != 0) {
                SimpleCategory simpleCategory;
                if (!simpleCategoryHashMap.containsKey(catID)) {
                    simpleCategory = new SimpleCategory();
                    simpleCategory.setCategoryID(catID);
                    simpleCategory.setTextGroupID(simpleDish.getCategoryTextGroupID());
                    simpleCategory.setName(simpleDish.getCategoryName());
                    simpleCategoryHashMap.put(catID, simpleCategory);
                    simpleCategories.add(simpleCategory);
                } else {
                    simpleCategory = simpleCategoryHashMap.get(catID);
                }
                simpleCategory.getSimpleDishes().add(simpleDish);
            } else {
                nullCategory.getSimpleDishes().add(simpleDish);
            }
        }
    }

    public ArrayList<SimpleCategory> getSimpleCategories() {
        return simpleCategories;
    }

    public void setCards(ArrayList<SimpleCard> cards) {
        this.cards = cards;
    }

    public ArrayList<SimpleCard> getCards() {
        return cards;
    }
}
