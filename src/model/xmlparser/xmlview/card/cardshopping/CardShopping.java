package model.xmlparser.xmlview.card.cardshopping;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class CardShopping {
    @ElementList(inline = true, name = "Shopping")
    public List<Shopping> shoppings;
}
