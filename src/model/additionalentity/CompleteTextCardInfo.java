package model.additionalentity;

import model.database.worldonlinedb.TextCardEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 14.11.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class CompleteTextCardInfo {
    private TextCardEntity textCardEntity;
    private CompleteTextGroupInfo completeTextGroupInfo;

    public CompleteTextCardInfo(TextCardEntity textCardEntity) {
        this.textCardEntity = textCardEntity;
    }

    public TextCardEntity getTextCardEntity() {
        return textCardEntity;
    }

    public void setTextCardEntity(TextCardEntity textCardEntity) {
        this.textCardEntity = textCardEntity;
    }

    public CompleteTextGroupInfo getCompleteTextGroupInfo() {
        return completeTextGroupInfo;
    }

    public void setCompleteTextGroupInfo(CompleteTextGroupInfo completeTextGroupInfo) {
        this.completeTextGroupInfo = completeTextGroupInfo;
    }
}
