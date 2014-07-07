package model.xmlparser;

import helper.FileHelper;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.TagType;
import model.database.requests.TagRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.TagEntity;
import model.database.worldonlinedb.TagGroupEntity;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import model.textparser.StringFileParser;
import model.textparser.StringIntPair;

import java.io.IOException;
import java.util.ArrayList;

public class TagParser {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, TagParser.class);

    public void saveTags(ArrayList<StringIntPair> tagList, TagType tagType) {
        TextGroupEntity textGroupEntity = new TextGroupEntity(tagType.toString() + "TagGroupName");
        TextEntity textRu = new TextEntity(LanguageType.Russian, tagType.getRussianName(), textGroupEntity);
        TextEntity textEn = new TextEntity(LanguageType.English, tagType.toString(), textGroupEntity);
        TextRequest.addText(textRu);
        TextRequest.addText(textEn);
        TagGroupEntity tagGroupEntity = new TagGroupEntity(textGroupEntity, null, tagType.getValue());
        TagRequest.addTagGroup(tagGroupEntity);
        try {
            for (StringIntPair tagItem : tagList) {
                TextGroupEntity textGroup = new TextGroupEntity(tagType + "_" + tagItem.getString());
                TagEntity tagEntity = new TagEntity(textGroup, tagItem.getString(), tagGroupEntity, null);
//                tagEntity.setTagID((long) tagItem.getAnInt());
                TagRequest.addTag(tagEntity);
                TagEntity tagEntitySaved = TagRequest.getTag(tagEntity.getTagID());
                loggerFactory.debug("tagEntity " + tagEntity.getTagID() + " " + tagEntity.getTagGroup().getTagGroupID());
                loggerFactory.debug("tagEntitySaved " + tagEntitySaved.getTagID());
                loggerFactory.debug("tagGroupEntitySaved " + " " + tagEntitySaved.getTagGroup().getTagGroupID());
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void parseTags() throws IOException {
        ArrayList<StringIntPair> categories = StringFileParser.parseStandardStringIntPair(FileHelper.readFileAsString(ServerConsts.root + "app_data/Categories.txt"), ";");
        ArrayList<StringIntPair> kitchens = StringFileParser.parseStandardStringIntPair(FileHelper.readFileAsString(ServerConsts.root + "app_data/Kitchens.txt"), ";");
        ArrayList<StringIntPair> ribbons = StringFileParser.parseStandardStringIntPair(FileHelper.readFileAsString(ServerConsts.root + "app_data/Ribbons.txt"), ";");
        saveTags(categories, TagType.Categories);
        saveTags(kitchens, TagType.Cuisine);
        saveTags(ribbons, TagType.Ribbons);
    }
}
