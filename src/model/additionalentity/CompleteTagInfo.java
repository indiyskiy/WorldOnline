package model.additionalentity;

import model.database.worldonlinedb.TagEntity;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 02.11.13
 * Time: 5:04
 * To change this template use File | Settings | File Templates.
 */
public class CompleteTagInfo {
    private TagEntity tagEntity;
    private HashMap<Long, CompleteTextGroupInfo> completeTextGroupInfoMap=new HashMap<Long, CompleteTextGroupInfo>();

    public CompleteTagInfo(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }

    public TagEntity getTagEntity() {
        return tagEntity;
    }

    public void setTagEntity(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }

    public HashMap<Long, CompleteTextGroupInfo> getCompleteTextGroupInfoMap() {
        return completeTextGroupInfoMap;
    }

    public void setCompleteTextGroupInfoMap(HashMap<Long, CompleteTextGroupInfo> completeTextGroupInfoMap) {
        this.completeTextGroupInfoMap = completeTextGroupInfoMap;
    }
}
