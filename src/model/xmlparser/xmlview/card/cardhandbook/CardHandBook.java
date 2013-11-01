package model.xmlparser.xmlview.card.cardhandbook;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 19.10.13
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
@Root(name="data")
public class CardHandBook {
    @ElementList(inline=true,name = "Handbook")
    public List<HandBook> handBooks;
}
