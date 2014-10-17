package model.xmlparser;

import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.CardParameterType;
import model.constants.databaseenumeration.DataType;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.ParameterRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.CardParameterTypeEntity;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;

public class ParameterParser {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, ParameterParser.class);

    public static void saveTypes() {
        for (CardParameterType cardParameterType : CardParameterType.values()) {
            String nameRus = cardParameterType.getRussianName();
            String nameEn = cardParameterType.getEnglishName();
            TextGroupEntity cardParameterTypeName = new TextGroupEntity(cardParameterType.toString() + "Name");
            TextEntity rusName = new TextEntity(LanguageType.Russian, nameRus, cardParameterTypeName);
            TextEntity enName = new TextEntity(LanguageType.English, nameEn, cardParameterTypeName);
            TextRequest.addText(rusName);
            TextRequest.addText(enName);
            DataType dataType = cardParameterType.getDataType();
            ApplicationBlock block = cardParameterType.getBlock();
            int position = cardParameterType.getValue();
            CardParameterTypeEntity cardParameterTypeEntity = new CardParameterTypeEntity(cardParameterTypeName, null, dataType, block, position, cardParameterType.isTranslatable(), cardParameterType.isMultiply());
            ParameterRequest.addCardParameterType(cardParameterTypeEntity);
        }
    }
}
