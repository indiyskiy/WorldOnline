package model.xmlparser.xmlview.card.cardhandbook;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class CardHandBook {
    @ElementList(inline = true, name = "Handbook")
    public List<HandBook> handBooks;
}
