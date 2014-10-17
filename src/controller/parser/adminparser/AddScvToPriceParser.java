package controller.parser.adminparser;

import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.DishRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.database.worldonlinedb.dishes.DishCategoryEntity;
import model.database.worldonlinedb.dishes.DishEntity;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.logger.LoggerFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.fileupload.FileUploadException;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

public class AddScvToPriceParser implements Runnable {
    HashMap<String, File> fileMap;
    Long priceID;
    PriceEntity priceEntity;
    Thread thread = new Thread(this);
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddScvToPriceParser.class);

    public AddScvToPriceParser(HttpServletRequest request) throws FileUploadException {
        ParsedRequest parsedRequest = ServletHelper.getParametersMap(request);
        HashMap<String, String> textMap = parsedRequest.getTextMap();
        fileMap = parsedRequest.getFileMap();
        priceID = Long.parseLong(textMap.get("priceID"));
        priceEntity = DishRequest.getPrice(priceID);
    }

    public void runAdding() {
        thread.start();
    }

    @Override
    public void run() {
        if (priceID != null && priceID != 0 && priceEntity != null && !fileMap.isEmpty()) {
            for (File file : fileMap.values()) {
                try {
                    CSVParser parser = CSVParser.parse(file, Charset.forName("UTF-8"), CSVFormat.RFC4180);
                    for (CSVRecord csvRecord : parser) {
                        String catEn = csvRecord.get(0);
                        String catRu = csvRecord.get(1);
                        String nameEn = csvRecord.get(2);
                        String nameRu = csvRecord.get(3);
                        Double cost = Double.parseDouble(csvRecord.get(6));
                        if (!DishRequest.isDishExist(nameRu, nameEn, cost, priceID)) {
                            loggerFactory.debug("add dish " + nameRu);
                            DishCategoryEntity dishCategoryEntity = DishRequest.findCategory(catEn, catRu, priceID);
                            if (dishCategoryEntity == null) {
                                dishCategoryEntity = new DishCategoryEntity();
                                dishCategoryEntity.setName(new TextGroupEntity("dishCategory" + catEn));
                                DishRequest.addDishCategory(dishCategoryEntity);
                                TextEntity ru = new TextEntity(LanguageType.Russian, catRu, dishCategoryEntity.getName());
                                TextEntity en = new TextEntity(LanguageType.English, catEn, dishCategoryEntity.getName());
                                TextRequest.addText(ru);
                                TextRequest.addText(en);
                            }
                            DishEntity dishEntity = new DishEntity();
                            dishEntity.setDishCategory(dishCategoryEntity);
                            dishEntity.setPrice(priceEntity);
                            dishEntity.setCost(cost);
                            dishEntity.setDishName(new TextGroupEntity("dishName" + nameEn));
                            DishRequest.addDish(dishEntity);
                            TextEntity ru = new TextEntity(LanguageType.Russian, nameRu, dishEntity.getDishName());
                            TextEntity en = new TextEntity(LanguageType.English, nameEn, dishEntity.getDishName());
                            TextRequest.addText(ru);
                            TextRequest.addText(en);
                        } else {
                            loggerFactory.debug("repeated dish  " + nameRu);
                        }
                    }
                } catch (IOException e) {
                    loggerFactory.error(e);
                }
            }
        }
    }

    public HashMap<String, File> getFileMap() {
        return fileMap;
    }

    public Long getPriceID() {
        return priceID;
    }

    public PriceEntity getPriceEntity() {
        return priceEntity;
    }
}
