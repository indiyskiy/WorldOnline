package model.additionalentity;

import java.util.ArrayList;

public class MenuInfo {
    private SimpleMenu menu;
    private ArrayList<SimpleMenu> submenus = new ArrayList<SimpleMenu>();
    private SimpleMenu parentMenu;

    public SimpleMenu getMenu() {
        return menu;
    }

    public void setMenu(SimpleMenu menu) {
        this.menu = menu;
    }

    public ArrayList<SimpleMenu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(ArrayList<SimpleMenu> submenus) {
        this.submenus = submenus;
    }

    public SimpleMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(SimpleMenu parentMenu) {
        this.parentMenu = parentMenu;
    }
}
