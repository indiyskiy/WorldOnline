package model.xmlparser.xmlview.photo.photocard;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class PhotoCard {
    @ElementList(inline = true, name = "Photo")
    public
    List<Photo> photos;
}
