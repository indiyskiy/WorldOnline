package model.xmlparser.xmlview.card.cardhotel;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class CardHotels {
    @ElementList(inline = true, name = "Hotel")
    public List<Hotel> hotels;
}
