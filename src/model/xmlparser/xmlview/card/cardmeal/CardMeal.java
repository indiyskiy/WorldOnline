package model.xmlparser.xmlview.card.cardmeal;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class CardMeal {
    @ElementList(inline = true, name = "Meal")
    public List<Meal> meals;
}
