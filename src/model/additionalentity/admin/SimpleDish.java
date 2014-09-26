package model.additionalentity.admin;

public class SimpleDish {
    private String name;
    private String categoryName;
    private Long dishID;
    private Long categoryID;
    private Long textGroupID;
    private Long categoryTextGroupID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getDishID() {
        return dishID;
    }

    public void setDishID(Long dishID) {
        this.dishID = dishID;
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

    public Long getCategoryTextGroupID() {
        return categoryTextGroupID;
    }

    public void setCategoryTextGroupID(Long categoryTextGroupID) {
        this.categoryTextGroupID = categoryTextGroupID;
    }
}
