package model.additionalentity;

import java.util.ArrayList;
import java.util.HashMap;

public class CompleteTextGroupInfo {
    private Long textGroupID;
    private String textGroupName;
    private ArrayList<CompleteTextInfo> completeTextInfos = new ArrayList<>();

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public String getTextGroupName() {
        return textGroupName;
    }

    public void setTextGroupName(String textGroupName) {
        this.textGroupName = textGroupName;
    }

    public ArrayList<CompleteTextInfo> getCompleteTextInfos() {
        return completeTextInfos;
    }

    public void setCompleteTextInfos(ArrayList<CompleteTextInfo> completeTextInfos) {
        this.completeTextInfos = completeTextInfos;
    }

    public HashMap<Integer, CompleteTextInfo> getTextMap() {
        HashMap<Integer, CompleteTextInfo> textMap = new HashMap<>();
        for (CompleteTextInfo completeTextInfo : completeTextInfos) {
            textMap.put(completeTextInfo.getLanguageType().getValue(), completeTextInfo);
        }
        return textMap;
    }
}
