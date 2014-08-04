package model.xmlparser.xmlview.card.cardrelax;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class CardRelax {
    @ElementList(inline = true, name = "Relax")
    public List<Relax> relaxes;
}
