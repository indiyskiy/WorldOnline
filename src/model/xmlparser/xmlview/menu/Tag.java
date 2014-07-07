package model.xmlparser.xmlview.menu;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Tag")
public class Tag {
    @Element(name = "TagID", required = false)
    public Integer tagID;

    public Tag getClone() {
        Tag clone = new Tag();
        clone.tagID = this.tagID;
        return clone;
    }
}
