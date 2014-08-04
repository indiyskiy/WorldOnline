package model.xmlparser.xmlview.card.cardsights;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class CardSight {
    @ElementList(inline = true, name = "Sight")
    public List<Sight> sights;
}
