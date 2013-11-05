package model.xmlparser.xmlview.photo.photocard;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class PhotoCard {
    @ElementList(inline = true, name = "Photo")
    List<Photo> photos;
}
