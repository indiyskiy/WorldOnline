package model.xmlparser.xmlview.card.cardshopping;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 20.10.13
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class CardShopping {
    @ElementList(inline = true, name = "Shopping")
    public List<Shopping> shoppings;
}
