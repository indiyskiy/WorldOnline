package controller.parser.adminparser;


import model.additionalentity.admin.CardCoordinate;
import model.database.requests.CardRequest;
import model.database.requests.CoordinateRequest;
import model.database.worldonlinedb.CardCoordinateEntity;
import model.database.worldonlinedb.CardEntity;

import javax.servlet.http.HttpServletRequest;

public class EditCardCoordinateParser {
    private CardEntity cardEntity;
    private CardCoordinateEntity cardCoordinate;

    public EditCardCoordinateParser(HttpServletRequest request) {
        cardCoordinate = CoordinateRequest.getCardCoordinate(Long.parseLong(request.getParameter("cardID")));
        cardEntity = cardCoordinate.getCard();
        cardCoordinate.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        cardCoordinate.setLongitude(Double.parseDouble(request.getParameter("longitude")));
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public CardCoordinateEntity getCardCoordinate() {
        return cardCoordinate;
    }
}
