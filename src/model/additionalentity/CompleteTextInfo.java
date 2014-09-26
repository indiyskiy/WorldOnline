package model.additionalentity;

import model.constants.databaseenumeration.LanguageType;

public class CompleteTextInfo {
    private String text = "";
    private Long textID;
    private LanguageType languageType;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTextID() {
        return textID;
    }

    public void setTextID(Long textID) {
        this.textID = textID;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }
}
