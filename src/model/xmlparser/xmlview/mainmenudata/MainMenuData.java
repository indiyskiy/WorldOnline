package model.xmlparser.xmlview.mainmenudata;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class MainMenuData {
    @ElementList(inline = true, name = "Submenu")
    List<Submenu> submenus;

    public List<Submenu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(List<Submenu> submenus) {
        this.submenus = submenus;
    }
}
