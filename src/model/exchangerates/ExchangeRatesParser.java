package model.exchangerates;

import controller.phone.parser.MobileParser;
import helper.FileHelper;
import helper.StringHelper;
import helper.TimeManager;
import helper.WebPageGetter;
import model.constants.Component;
import model.constants.ServerConsts;
import model.database.requests.ExchangeRatesRequest;
import model.database.worldonlinedb.ExchangeRatesEntity;
import model.logger.LoggerFactory;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class ExchangeRatesParser {
    private final String url = "http://www.cbr.ru/scripts/XML_daily.asp";

    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Global, ExchangeRatesParser.class);

    public ValCurs saveCbrRates() {
        try {
            File file = getCbrRatesToFile();
            if (file != null) {
                ValCurs valCurs = getExchangeRatesFromFile(file);
                if (valCurs != null) {
                    saveToDB(valCurs);
                    return valCurs;
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    private void saveToDB(ValCurs valCurs) {
//        loggerFactory.debug(valCurs.name + " " + valCurs.date);
        Double eur = 0.0;
        Double usd = 0.0;
        for (Valute valute : valCurs.valutes) {
            if (valute.charCode != null) {
                if (valute.charCode.equals("EUR")) {
                    eur = Double.parseDouble(valute.value.replaceAll(",", "."));
                }
                if (valute.charCode.equals("USD")) {
                    usd = Double.parseDouble(valute.value.replaceAll(",", "."));
                }
                if (eur != 0.0 && usd != 0.0) {
                    break;
                }
            }
        }
        ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity();
        exchangeRatesEntity.setEur(eur);
        exchangeRatesEntity.setUsd(usd);
        exchangeRatesEntity.setDownloadTimestamp(TimeManager.currentTime());
        Timestamp timestamp = TimeManager.getTimestampFromExchangeTime(valCurs.date);
        exchangeRatesEntity.setActualDayTimestamp(timestamp);
        ExchangeRatesRequest.saveExchangeRates(exchangeRatesEntity);
    }

    private ValCurs getExchangeRatesFromFile(File file) {
        Persister serializer = new Persister();
        try {
            return serializer.read(ValCurs.class, file, false);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public File getCbrRatesToFile() {
        try {
            String textFromPage = WebPageGetter.getTextFromPage(url);
            textFromPage = StringHelper.convertEncoding(textFromPage, "cp1251", "utf-8");
            File file = FileHelper.saveToFile(textFromPage, ServerConsts.tempFileStore, "exchangeRates.xml");
            return file;
        } catch (IOException e) {
            loggerFactory.error(e);
        }
        return null;
    }

}
