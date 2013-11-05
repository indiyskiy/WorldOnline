package model.xmlparser;

import model.xmlparser.xmlview.photo.photocard.PhotoCard;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class PhotoParser {
    public PhotoCard getPhotoCard(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(PhotoCard.class, reader, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        PhotoParser photoParser = new PhotoParser();
        PhotoCard mainMenuData = photoParser.getPhotoCard("D:\\program\\photocards.xml");
    }
}
