package model.additionalentity;


import model.additionalentity.admin.CardMenu;

import java.util.ArrayList;

public class ParentMenu {
    private String name;
    private Long parentMenuID;
    private ArrayList<SimpleMenu> cardMenus = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentMenuID() {
        return parentMenuID;
    }

    public void setParentMenuID(Long parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

    public ArrayList<SimpleMenu> getCardMenus() {
        return cardMenus;
    }

    public void setCardMenus(ArrayList<SimpleMenu> cardMenus) {
        this.cardMenus = cardMenus;
    }
}
