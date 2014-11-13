package model.phone.responseentity;


import com.google.gson.JsonObject;

public class UserGlobalUpdateStatus {
    boolean needToTagsUpdate;
    boolean needToMenusUpdate;
    boolean needToParameterTypeUpdate;
    boolean needToDishTagsUpdate;
    boolean needToDishCategoriesUpdate;

    public boolean isNeedToTagsUpdate() {
        return needToTagsUpdate;
    }

    public void setNeedToTagsUpdate(boolean needToTagsUpdate) {
        this.needToTagsUpdate = needToTagsUpdate;
    }

    public boolean isNeedToMenusUpdate() {
        return needToMenusUpdate;
    }

    public void setNeedToMenusUpdate(boolean needToMenusUpdate) {
        this.needToMenusUpdate = needToMenusUpdate;
    }

    public boolean isNeedToParameterTypeUpdate() {
        return needToParameterTypeUpdate;
    }

    public void setNeedToParameterTypeUpdate(boolean needToParameterTypeUpdate) {
        this.needToParameterTypeUpdate = needToParameterTypeUpdate;
    }

    public boolean isNeedToDishTagsUpdate() {
        return needToDishTagsUpdate;
    }

    public void setNeedToDishTagsUpdate(boolean needToDishTagsUpdate) {
        this.needToDishTagsUpdate = needToDishTagsUpdate;
    }

    public boolean isNeedToDishCategoriesUpdate() {
        return needToDishCategoriesUpdate;
    }

    public void setNeedToDishCategoriesUpdate(boolean needToDishCategoriesUpdate) {
        this.needToDishCategoriesUpdate = needToDishCategoriesUpdate;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("needToTagsUpdate", needToTagsUpdate);
        jsonObject.addProperty("needToMenusUpdate", needToMenusUpdate);
        jsonObject.addProperty("needToParameterTypeUpdate", needToParameterTypeUpdate);
        jsonObject.addProperty("needToDishTagsUpdate", needToDishTagsUpdate);
        jsonObject.addProperty("needToDishCategoriesUpdate", needToDishCategoriesUpdate);
        return jsonObject;
    }
}
