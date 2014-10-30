package model.phone.requesthandler;


import controller.phone.entity.CardByListRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.CardRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.CardByListResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class CardByListHandler implements MobileHandler {


    public CardByListResponse handleRequest(CardByListRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        CardByListResponse cardByListResponse = new CardByListResponse();
        cardByListResponse.setMobileCards(CardRequest.getMobileCards(mobileRequest.getUserID(), mobileRequest.getCardIDs()));
        return cardByListResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != CardByListRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, CardByListRequest.class);
        }
        CardByListRequest cardByListRequest = (CardByListRequest) mobileRequest;
        return handleRequest(cardByListRequest);
    }
}
