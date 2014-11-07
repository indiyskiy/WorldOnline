package model.phone.requesthandler;


import controller.phone.entity.PricesForUpdateRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.PricesUpdateAggregator;
import model.database.requests.UserDataRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.PricesForUpdateResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class PricesForUpdateHandler implements MobileHandler {
    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != PricesForUpdateRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, PricesForUpdateRequest.class);
        }
        PricesForUpdateRequest pricesForUpdateRequest = (PricesForUpdateRequest) mobileRequest;
        return handleRequest(pricesForUpdateRequest);
    }

    public PricesForUpdateResponse handleRequest(PricesForUpdateRequest pricesForUpdateRequest) {
        PricesUpdateAggregator cardUpdateAggregator = UserDataRequest.getPricesUpdateInfo(pricesForUpdateRequest.getUserID());
        PricesForUpdateResponse pricesForUpdateResponse = new PricesForUpdateResponse();
        pricesForUpdateResponse.setPricesUpdateAggregator(cardUpdateAggregator);
        return pricesForUpdateResponse;
    }
}
