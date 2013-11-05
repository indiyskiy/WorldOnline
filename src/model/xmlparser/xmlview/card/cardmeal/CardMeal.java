package model.xmlparser.xmlview.card.cardmeal;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 19.10.13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class CardMeal {
    @ElementList(inline = true, name = "Meal")
    public List<Meal> meals;
}
