package model.additionalentity;

import model.database.worldonlinedb.ImageEntity;
import model.database.worldonlinedb.MenuEntity;

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
    private HashMap<Long,CompleteMenuInfo> kids=new HashMap<Long, CompleteMenuInfo>();
    private CompleteTextGroupInfo completeTextGroupInfo;
    private ImageEntity image;

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
}
