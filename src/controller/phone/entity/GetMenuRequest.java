package controller.phone.entity;

/**
 * Created by Илья on 10.04.14.
 */
public class GetMenuRequest extends MobileRequest {
    private Long menuID;

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public Long getMenuID() {
        return menuID;
    }
}
