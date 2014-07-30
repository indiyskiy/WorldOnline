package model.xmlparser.xmlview.menu;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "Dish")
public class Dish {
    @Attribute(name = "id")
    public Integer id;
    @Element(name = "NameRu", required = false)
    public String nameRu;
    @Element(name = "NameEn", required = false)
    public String nameEn;
    @ElementList(inline = true, name = "Tags", type = Tag.class, required = false)
    public ArrayList<Tag> tags = new ArrayList<>();
    @Element(name = "Price", required = false)
    public String price;
    @Element(name = "RestID", required = false)
    public Integer restID;
    @Element(name = "Show", required = false)
    public boolean show;

    public boolean equals(Object obj) {
        if (obj.getClass() != Dish.class) {
            return false;
        }
        Dish that = (Dish) obj;
        if (this.restID == null && that.restID != null) {
            return false;
        }
        if (this.restID != null && !this.restID.equals(that.restID)) {
            return false;
        }
        if (this.nameEn == null && that.nameEn != null) {
            return false;
        }
        if (this.nameEn != null && !this.nameEn.equals(that.nameEn)) {
            return false;
        }
        if (this.nameRu == null && that.nameRu != null) {
            return false;
        }
        if (this.nameRu != null && !this.nameRu.equals(that.nameRu)) {
            return false;
        }
        if (this.price == null && that.price != null) {
            return false;
        }
        if (this.price != null && !this.price.equals(that.price)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 0;
        if (restID != null) {
            hash = hash * 13 + restID.hashCode();
        }
        if (nameEn != null) {
            hash = hash * 13 + nameEn.hashCode();
        }
        if (nameRu != null) {
            hash = hash * 13 + nameRu.hashCode();
        }
        if (price != null) {
            hash = hash * 13 + price.hashCode();
        }
        if (show) {
            hash += 13;
        }
        return hash;
    }
}
