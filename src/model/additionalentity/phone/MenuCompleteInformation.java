package model.additionalentity.phone;

/**
 * Created by Илья on 11.04.14.
 */
public class MenuCompleteInformation {
    private Long menuID;
    private Long iconImageID;
    private Long parentMenuID;
    private String text;
    private Integer number;

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public Long getMenuID() {
        return menuID;
    }

    public void setIconImageID(Long iconImageID) {
        this.iconImageID = iconImageID;
    }

    public Long getIconImageID() {
        return iconImageID;
    }


    public void setParentMenuID(Long parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

    public Long getParentMenuID() {
        return parentMenuID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}
