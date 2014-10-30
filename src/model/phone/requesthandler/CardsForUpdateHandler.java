package model.phone.requesthandler;

import controller.phone.entity.CardsForUpdateRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.CardUpdateAggregator;
import model.database.requests.UserDataRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.CardsForUpdateResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class CardsForUpdateHandler implements MobileHandler {

    public CardsForUpdateResponse handleRequest(CardsForUpdateRequest cardsForUpdateRequest) {
        CardUpdateAggregator cardUpdateAggregator = UserDataRequest.getCardUpdateInfo(cardsForUpdateRequest.getUserID());
        CardsForUpdateResponse cardsForUpdateResponse = new CardsForUpdateResponse();
        cardsForUpdateResponse.setCardUpdateAggregator(cardUpdateAggregator);
        return cardsForUpdateResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != CardsForUpdateRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, CardsForUpdateRequest.class);
        }
        CardsForUpdateRequest cardsForUpdateRequest = (CardsForUpdateRequest) mobileRequest;
        return handleRequest(cardsForUpdateRequest);
    }
}
