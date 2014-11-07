package model.phone.requesthandler;


import controller.phone.entity.PriceByListRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.DishRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.PriceByListResponse;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class PricesByListHandler implements MobileHandler {
    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != PriceByListRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, PriceByListRequest.class);
        }
        PriceByListRequest priceByListRequest = (PriceByListRequest) mobileRequest;
        return handleRequest(priceByListRequest);
    }


    public PriceByListResponse handleRequest(PriceByListRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        PriceByListResponse priceByListResponse = new PriceByListResponse();
        priceByListResponse.setMobilePrices(DishRequest.getAllMobilePrices(mobileRequest.getUserID(), mobileRequest.getPriceIDs()));
        return priceByListResponse;
    }
}
