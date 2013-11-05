package model.xmlparser.xmlview.card.cardrelax;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 20.10.13
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class CardRelax {
    @ElementList(inline = true, name = "Relax")
    public List<Relax> relaxes;
}
