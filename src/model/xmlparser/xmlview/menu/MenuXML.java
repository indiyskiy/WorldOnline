package model.xmlparser.xmlview.menu;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;


@Root(name = "data")
public class MenuXML {

    @ElementList(inline = true, name = "DishEntity", required = false)
    public ArrayList<Dish> dishes = new ArrayList<Dish>();

}
