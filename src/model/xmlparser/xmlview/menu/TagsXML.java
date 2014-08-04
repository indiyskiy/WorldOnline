package model.xmlparser.xmlview.menu;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "data")
public class TagsXML {
    @ElementList(inline = true, name = "Tags", required = true)
    public ArrayList<CompleteTag> tags = new ArrayList<CompleteTag>();

}
