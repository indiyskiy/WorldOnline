package controller.phone.entity;

public class MenuRequest extends MobileRequest {
    private Long menuID;

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public Long getMenuID() {
        return menuID;
    }
}
