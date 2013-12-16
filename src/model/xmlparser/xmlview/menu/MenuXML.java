package model.xmlparser.xmlview.menu;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.*;
import java.util.ArrayList;


@Root(name = "data")
public class MenuXML {

    @ElementList(inline = true, name = "Dish", required = false)
    public ArrayList<Dish> dishes = new ArrayList<Dish>();


    public static MenuXML loadMenuXml() throws Exception {
        Serializer serializer = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
        File file = new File("MenuXML.xml");
        return serializer.read(MenuXML.class, file);
    }
}
