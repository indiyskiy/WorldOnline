package model.constants.databaseenumeration;


public interface TagEnum {

    public int getValue();

    public String getNameRu();

    public String getNameEn();

    public String getImageName();

    public TagEnum setByValue(String value);

    public TagType getTagType();

    TagEnum[] allValues();
}
