package model.xmlparser.xmlview.menu;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 04.12.13
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */

@Root(name = "data")
public class TagsXML {
    @ElementList(inline = true, name = "Tags", required = false)
    public ArrayList<CompleteTag> tags = new ArrayList<CompleteTag>();

    public static TagsXML loadTagsXML() throws Exception {
        Serializer serializer = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
        File file = new File("TagsXML.xml");
        return serializer.read(TagsXML.class, file);
    }
}
