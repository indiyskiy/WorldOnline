package model.additionalentity.admin;

import model.additionalentity.CompleteTextGroupInfo;
import model.database.worldonlinedb.TagEntity;

import java.util.HashMap;

public class CompleteTagInfo {
    private TagEntity tagEntity;
    private HashMap<Long, CompleteTextGroupInfo> completeTextGroupInfoMap = new HashMap<Long, CompleteTextGroupInfo>();

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
