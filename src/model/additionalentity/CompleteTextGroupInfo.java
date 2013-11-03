package model.additionalentity;

import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 02.11.13
 * Time: 7:09
 * To change this template use File | Settings | File Templates.
 */
public class CompleteTextGroupInfo {
    private TextGroupEntity textGroup;
    private HashMap<Long,TextEntity> textEntityMap;

    public CompleteTextGroupInfo(TextGroupEntity textGroup) {
        this.textGroup = textGroup;
        textEntityMap=new HashMap<Long, TextEntity>();
    }

    public TextGroupEntity getTextGroup() {
        return textGroup;
    }

    public void setTextGroup(TextGroupEntity textGroup) {
        this.textGroup = textGroup;
    }

    public HashMap<Long, TextEntity> getTextEntityMap() {
        return textEntityMap;
    }

    public void setTextEntityMap(HashMap<Long, TextEntity> textEntityMap) {
        this.textEntityMap = textEntityMap;
    }
}
