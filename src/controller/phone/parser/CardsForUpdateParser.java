package controller.phone.parser;

import controller.phone.entity.CardsForUpdateRequest;
import controller.phone.entity.MobileRequest;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class CardsForUpdateParser extends MobileParser {

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException {
        CardsForUpdateRequest cardsForUpdateRequest = new CardsForUpdateRequest();
        cardsForUpdateRequest.setUserID(Long.parseLong(request.getParameter("userID")));
        return cardsForUpdateRequest;
    }
}
