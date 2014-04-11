package controller.phone.entity;

/**
 * Created by Илья on 10.04.14.
 */
public class GetMenuRequest extends MobileRequest {
    private Long userID;
    private Long menuID;

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public Long getMenuID() {
        return menuID;
    }
}
