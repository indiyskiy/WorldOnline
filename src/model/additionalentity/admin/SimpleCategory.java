package model.additionalentity.admin;


import java.util.ArrayList;

public class SimpleCategory {
    private String name;
    private Long categoryID;
    private Long textGroupID;
    ArrayList<SimpleDish> simpleDishes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public ArrayList<SimpleDish> getSimpleDishes() {
        return simpleDishes;
    }

    public void setSimpleDishes(ArrayList<SimpleDish> simpleDishes) {
        this.simpleDishes = simpleDishes;
    }
}
