package model.xmlparser.xmlview.mainmenudata;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class MainMenuData {
    @ElementList(inline = true,name = "Submenu")
    List<Submenu> submenus;
}
