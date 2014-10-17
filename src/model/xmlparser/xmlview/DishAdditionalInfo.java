package model.xmlparser.xmlview;

public class DishAdditionalInfo {
    private Integer parentID;
    private Integer order;
    private String nameEng;
    private String nameRus;
    private Integer catID;
    private String categoryNameRus;
    private String categoryNameEng;
    private String price;

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public Integer getCatID() {
        return catID;
    }

    public void setCatID(Integer catID) {
        this.catID = catID;
    }

    public String getCategoryNameRus() {
        return categoryNameRus;
    }

    public void setCategoryNameRus(String categoryNameRus) {
        this.categoryNameRus = categoryNameRus;
    }

    public String getCategoryNameEng() {
        return categoryNameEng;
    }

    public void setCategoryNameEng(String categoryNameEng) {
        this.categoryNameEng = categoryNameEng;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
