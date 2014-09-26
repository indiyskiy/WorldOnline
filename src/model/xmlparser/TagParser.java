package model.xmlparser;

import helper.FileHelper;
import helper.ImageHelper;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.*;
import model.database.requests.TagRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.*;
import model.logger.LoggerFactory;
import model.textparser.StringFileParser;
import model.textparser.StringIntPair;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TagParser {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, TagParser.class);
    private static final String freePassText = "Бесплатно";

    public static void saveTags(ArrayList<StringIntPair> tagList, TagType tagType) {
        TextGroupEntity textGroupEntity = new TextGroupEntity(tagType.toString() + "TagGroupName");
        TextEntity textRu = new TextEntity(LanguageType.Russian, tagType.getRussianName(), textGroupEntity);
        TextEntity textEn = new TextEntity(LanguageType.English, tagType.toString(), textGroupEntity);
        TextRequest.addText(textRu);
        TextRequest.addText(textEn);
        TagGroupEntity tagGroupEntity = new TagGroupEntity(textGroupEntity, null, tagType.getValue(), tagType.getApplicationBlock(), tagType.getTagViewType());
        TagRequest.addTagGroup(tagGroupEntity);
        try {
            for (StringIntPair tagItem : tagList) {
                TextGroupEntity textGroup = new TextGroupEntity(tagType + "_" + tagItem.getString());
                TagEntity tagEntity = new TagEntity(textGroup, tagGroupEntity, null);
                TextEntity nameRu = new TextEntity(LanguageType.Russian, tagItem.getString(), textGroup);
                TextEntity nameEn = new TextEntity(LanguageType.English, tagItem.getString(), textGroup);
                TextRequest.addText(nameRu);
                TextRequest.addText(nameEn);
//                tagEntity.setTagID((long) tagItem.getAnInt());
                TagRequest.addTag(tagEntity);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public static void parseTags() throws IOException {
        ArrayList<StringIntPair> categories = StringFileParser.parseStandardStringIntPair(FileHelper.readFileAsString(ServerConsts.root + "app_data/Categories.txt"), ";");
        ArrayList<StringIntPair> kitchens = StringFileParser.parseStandardStringIntPair(FileHelper.readFileAsString(ServerConsts.root + "app_data/Kitchens.txt"), ";");
        ArrayList<StringIntPair> ribbons = StringFileParser.parseStandardStringIntPair(FileHelper.readFileAsString(ServerConsts.root + "app_data/Ribbons.txt"), ";");
        saveTags(categories, TagType.Categories);
        saveTags(kitchens, TagType.Cuisine);
        saveTags(ribbons, TagType.Ribbons);
        saveHardCodedTags();
    }

    private static void saveHardCodedTags() {
        addMiddlePrice();
        addFreePass();
        addNoBarriers();
        addNewsTags();
    }

    private static void addMiddlePrice() {
        addTagEnum(MiddlePrice.big);
    }

    private static void addFreePass() {
        ArrayList<StringIntPair> tagList = new ArrayList<>();
        tagList.add(new StringIntPair(freePassText, 0));
        saveTags(tagList, TagType.PetersburgCard);
    }

    private static void addNoBarriers() {
        addTagEnum(NoBarrierTag.a);
    }

    private static void addNewsTags() {
        addTagEnum(NewsTag.free);
    }

    private static void addTagEnum(TagEnum tagEnum) {
        TagType tagType = tagEnum.getTagType();
        TextGroupEntity textGroupEntity = new TextGroupEntity(tagType.toString() + "TagGroupName");
        TextEntity textRu = new TextEntity(LanguageType.Russian, tagType.getRussianName(), textGroupEntity);
        TextEntity textEn = new TextEntity(LanguageType.English, tagType.getEnglishName(), textGroupEntity);
        TextRequest.addText(textRu);
        TextRequest.addText(textEn);
        TagGroupEntity tagGroupEntity = new TagGroupEntity(textGroupEntity, null, tagType.getValue(), tagType.getApplicationBlock(), tagType.getTagViewType());
        TagRequest.addTagGroup(tagGroupEntity);
        try {
            for (TagEnum tagEnumValue : tagEnum.allValues()) {
                TextGroupEntity textGroup = new TextGroupEntity(tagType + "_" + tagEnumValue);
                ImageEntity imageEntity = ImageHelper.saveImage(tagEnumValue.getImageName(), ImageType.Tag);
                TagEntity tagEntity = new TagEntity(textGroup, tagGroupEntity, imageEntity);
                TextEntity tagNameRu = new TextEntity(LanguageType.Russian, tagEnumValue.getNameRu(), textGroup);
                TextEntity tagNameEn = new TextEntity(LanguageType.English, tagEnumValue.getNameEn(), textGroup);
                TextRequest.addText(tagNameEn);
                TextRequest.addText(tagNameRu);
                TagRequest.addTag(tagEntity);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public static void saveCardTags(String stringOfTags, CardEntity card, TagType tagType) throws SQLException, IOException {
        if (stringOfTags == null || stringOfTags.equals("") || stringOfTags.equals("null")) {
            return;
        }
        ArrayList<Integer> integers = StringFileParser.getIntegerListByString(stringOfTags, ",");
        String fileText = "";
        if (tagType.equals(TagType.Cuisine)) {
            fileText = FileHelper.readFileAsString(ServerConsts.root + "app_data/Kitchens.txt");
        } else {
            if (tagType.equals(TagType.Categories)) {
                fileText = FileHelper.readFileAsString(ServerConsts.root + "app_data/Categories.txt");
            } else {
                fileText = FileHelper.readFileAsString(ServerConsts.root + "app_data/Ribbons.txt");
            }
        }
        ArrayList<StringIntPair> stringIntPairs = StringFileParser.parseStandardStringIntPair(fileText, ";");
        for (Integer integer : integers) {
            for (StringIntPair stringIntPair : stringIntPairs) {
                if (integer == stringIntPair.getAnInt()) {
                    TagEntity tagEntity = TagRequest.getTag(stringIntPair.getString());
                    if (tagEntity != null) {
                        CardTagEntity cardTagEntity = new CardTagEntity(card, tagEntity);
                        TagRequest.addCardTag(cardTagEntity);
                    }
                }
            }
        }
    }

    public static void saveCardFreePass(String freePass, CardEntity cardEntity) {
        try {
            if (freePass == null || freePass.replaceAll(" ", "").isEmpty()) {
                return;
            }
            if (freePass.equals("YES")) {
                TagEntity tagEntity = TagRequest.getTag(freePassText);
                if (tagEntity != null) {
                    CardTagEntity cardTagEntity = new CardTagEntity(cardEntity, tagEntity);
                    TagRequest.addCardTag(cardTagEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveCardTag(String value, CardEntity card, TagEnum tagEnum) {
        try {
            if (value == null || value.replaceAll(" ", "").isEmpty()) {
                return;
            }
            TagEnum tagEnumValue = tagEnum.setByValue(value);
            if (tagEnumValue != null) {
                TagEntity tagEntity = TagRequest.getTag(tagEnumValue.getNameRu());
                if (tagEntity != null) {
                    CardTagEntity cardTagEntity = new CardTagEntity(card, tagEntity);
                    TagRequest.addCardTag(cardTagEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveCardNoBarrier(String freePass, CardEntity card) {
        try {
            if (freePass == null || freePass.replaceAll(" ", "").isEmpty()) {
                return;
            }
            String[] tags = freePass.split(",");
            for (String tag : tags) {
                if (tag != null && !tag.replaceAll(" ", "").isEmpty()) {
                    NoBarrierTag noBarrierTag = NoBarrierTag.parse(tag);
                    if (noBarrierTag != null) {
                        TagEntity tagEntity = TagRequest.getTag(noBarrierTag.getNameRu());
                        if (tagEntity != null) {
                            CardTagEntity cardTagEntity = new CardTagEntity(card, tagEntity);
                            TagRequest.addCardTag(cardTagEntity);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveCardMiddlePrice(String middlePrice, CardEntity card) {
        saveCardTag(middlePrice, card, MiddlePrice.big);
    }
}

