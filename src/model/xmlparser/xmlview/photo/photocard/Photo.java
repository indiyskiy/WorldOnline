package model.xmlparser.xmlview.photo.photocard;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Photo")
public class Photo {
    @Attribute(name = "id", required = true)
    public String id;

    @Element(name = "ParentMenuID", required = false)
    public String parentMenuID;
    @Element(name = "NameEn", required = false)
    public String nameEn;
    @Element(name = "NameRu", required = false)
    public String nameRu;
    @Element(name = "Filename", required = false)
    public String fileName;
    @Element(name = "lat", required = false)
    public String lat;
    @Element(name = "lon", required = false)
    public String lon;
    @Element(name = "PlaceCard", required = false)
    public String placeCard;
    @Element(name = "PeopleCard", required = false)
    public String peopleCard;
}

