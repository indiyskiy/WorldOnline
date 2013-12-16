package model.xmlparser.xmlview.menu;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 02.12.13
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "Tag")
public class Tag {
    @Element(name = "TagID", required = false)
    public Integer tagID;

    public Tag getClone() {
        Tag clone=new Tag();
        clone.tagID=this.tagID;
        return clone;
    }
}
