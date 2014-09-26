package model.additionalentity.admin;


import java.util.ArrayList;
import java.util.HashMap;

public class CompletePriceInfo {
    private Long priceID;
    private String name;
    private Long textGroupID;
    private ArrayList<SimpleCategory> simpleCategories = new ArrayList<>();
    private ArrayList<SimpleDish> categories;

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

    public ArrayList<SimpleCategory> getSimpleCategories() {
        return simpleCategories;
    }

    public void setSimpleCategories(ArrayList<SimpleDish> simpleDishes) {
        HashMap<Long, SimpleCategory> simpleCategoryHashMap = new HashMap<>();
        for (SimpleDish simpleDish : simpleDishes) {
            Long catID = simpleDish.getCategoryID();
            if (catID != null && catID != 0) {
                SimpleCategory simpleCategory;
                if (!simpleCategoryHashMap.containsKey(catID)) {
                    simpleCategory = new SimpleCategory();
                    simpleCategory.setCategoryID(catID);
                    simpleCategory.setTextGroupID(simpleDish.getCategoryTextGroupID());
                    simpleCategory.setName(simpleDish.getCategoryName());
                }
            }
        }
    }

    public void setCategories(ArrayList<SimpleDish> categories) {
        this.categories = categories;
    }

    public ArrayList<SimpleDish> getCategories() {
        return categories;
    }
}
