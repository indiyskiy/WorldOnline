package model.xmlparser.xmlview.mainmenudata;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "Submenu")
public class Submenu {
    @Attribute(name = "id", required = true)
    public String id;
    @Element(name = "MainMenuID", required = false)
    public String mainMenuID;
    @Element(name = "NameRU", required = false)
    public String nameRU;
    @Element(name = "NameEN", required = false)
    public String nameEN;
    @Element(name = "Image", required = false)
    public String image;
    @Element(name = "OrderID", required = false)
    public String orderID;
    @Element(name = "Ribbon", required = false)
    public String ribbon;

    public Long number;

    public Submenu(String id, String mainMenuID, String nameRU, String nameEN, String image, String orderID, String ribbon) {
        this.id = id;
        this.mainMenuID = mainMenuID;
        this.nameRU = nameRU;
        this.nameEN = nameEN;
        this.image = image;
        this.orderID = orderID;
        this.ribbon = ribbon;
    }

    public Submenu() {
    }
}
