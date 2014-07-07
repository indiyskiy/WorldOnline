package model.xmlparser.xmlview.menu;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "CompleteTag")
public class CompleteTag {
    @Attribute(name = "id")
    public Integer id;

    @Element(name = "NameRu", required = false)
    public String nameRu;


    @Element(name = "NameEn", required = false)
    public String nameEn;
}
