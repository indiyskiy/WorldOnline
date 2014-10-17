package model.phone.requesthandler;

import controller.phone.entity.AllPricesRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.DishRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllPricesResponse;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class AllPricesHandler implements MobileHandler {

    @Override
    public AllPricesResponse handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != AllPricesRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllPricesRequest.class);
        }
        AllPricesRequest allCardParameterTypesRequest = (AllPricesRequest) mobileRequest;
        return handleRequest(allCardParameterTypesRequest);
    }

    private AllPricesResponse handleRequest(AllPricesRequest allCardParameterTypesRequest) {
        AllPricesResponse allPricesResponse = new AllPricesResponse();
        allPricesResponse.setMobilePrices(DishRequest.getAllMobilePrices(allCardParameterTypesRequest.getUserID(), allCardParameterTypesRequest.getLimit(), allCardParameterTypesRequest.getOffset()));
        return allPricesResponse;
    }
}
