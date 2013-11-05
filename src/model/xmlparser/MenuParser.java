package model.xmlparser;

import model.xmlparser.xmlview.mainmenudata.MainMenuData;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class MenuParser {
    public MainMenuData getMainMenuData(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(MainMenuData.class, reader, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        MenuParser menuParser = new MenuParser();
        MainMenuData mainMenuData = menuParser.getMainMenuData("D:\\program\\MainMenuData.xml");
    }
}
