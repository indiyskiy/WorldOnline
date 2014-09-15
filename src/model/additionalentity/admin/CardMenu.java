package model.additionalentity.admin;

public class CardMenu {
    private long cardMenuID;
    private long menuID;
    private String name;

    public void setMenuID(long menuID) {
        this.menuID = menuID;
    }

    public long getMenuID() {
        return menuID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getCardMenuID() {
        return cardMenuID;
    }

    public void setCardMenuID(long cardMenuID) {
        this.cardMenuID = cardMenuID;
    }
}
