package model.additionalentity;

import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.worldonlinedb.ImageEntity;
import model.database.worldonlinedb.MenuEntity;
import model.database.worldonlinedb.TextEntity;
import model.logger.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 20.01.14
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class CompleteMenuInfo {
    private MenuEntity menuEntity;
    private HashMap<Long, CompleteMenuInfo> kids = new HashMap<Long, CompleteMenuInfo>();
    private CompleteTextGroupInfo completeTextGroupInfo;
    private ImageEntity image;
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Global, CompleteMenuInfo.class);

    public CompleteMenuInfo(MenuEntity menuEntity) {
        this.menuEntity = menuEntity;
    }

    public MenuEntity getMenuEntity() {
        return menuEntity;
    }

    public void setMenuEntity(MenuEntity menuEntity) {
        this.menuEntity = menuEntity;
    }

    public HashMap<Long, CompleteMenuInfo> getKids() {
        return kids;
    }

    public CompleteTextGroupInfo getCompleteTextGroupInfo() {
        return completeTextGroupInfo;
    }

    public void setCompleteTextGroupInfo(CompleteTextGroupInfo completeTextGroupInfo) {
        this.completeTextGroupInfo = completeTextGroupInfo;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public SimpleMenu getSimpleMenu() {
        SimpleMenu simpleMenu = new SimpleMenu();
        simpleMenu.setMenuID(menuEntity.getMenuID());
        Collection<TextEntity> textEntities = completeTextGroupInfo.getTextEntityMap().values();
        simpleMenu.setMenuName("no name-no texts");
        int i = 0;
        for (TextEntity textEntity : textEntities) {
            if (textEntity.getLanguageID() == LanguageType.Russian.getValue()
                    && textEntity.getText() != null
                    && !textEntity.getText().isEmpty()) {
                simpleMenu.setMenuName(textEntity.getText());
                return simpleMenu;
            } else {
                simpleMenu.setMenuName("no name " + i);
            }
            i++;
        }
        return simpleMenu;
    }
}
