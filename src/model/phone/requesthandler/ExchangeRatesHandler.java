package model.phone.requesthandler;

import controller.phone.entity.ExchangeRatesRequest;
import controller.phone.entity.MobileRequest;
import model.database.worldonlinedb.ExchangeRatesEntity;
import model.exception.IllegalTypeException;
import model.phone.responseentity.ExchangeRatesResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class ExchangeRatesHandler implements MobileHandler {
    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != ExchangeRatesRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, ExchangeRatesRequest.class);
        }
        ExchangeRatesRequest exchangeRatesRequest = (ExchangeRatesRequest) mobileRequest;
        return handleRequest(exchangeRatesRequest);
    }


    public MobileResponseEntity handleRequest(ExchangeRatesRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        ExchangeRatesEntity exchangeRatesEntity = model.database.requests.ExchangeRatesRequest.getActualExchangeRates();
        return new ExchangeRatesResponse(exchangeRatesEntity);
    }
}
