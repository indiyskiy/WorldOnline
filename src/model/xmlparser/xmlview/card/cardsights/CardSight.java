package model.xmlparser.xmlview.card.cardsights;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class CardSight {
    @ElementList(inline = true, name = "Sight")
    public List<Sight> sights;
}
