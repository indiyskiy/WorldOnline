package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;
import model.database.worldonlinedb.ExchangeRatesEntity;

import java.sql.Timestamp;

public class ExchangeRatesResponse extends MobileResponseEntity {

    private final Timestamp actualDayTimeStamp;
    private final Double eur;
    private final Double usd;

    public ExchangeRatesResponse(ExchangeRatesEntity exchangeRatesEntity) {
        super(Status.OK);
        this.actualDayTimeStamp = exchangeRatesEntity.getActualDayTimestamp();
        this.eur = exchangeRatesEntity.getEur();
        this.usd = exchangeRatesEntity.getUsd();
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("eur", eur);
        jsonObject.addProperty("usd", usd);
        jsonObject.addProperty("day", actualDayTimeStamp.toString());
        return jsonObject;
    }
}
