package controller.parser.adminparser;

import model.database.requests.CardRequest;
import model.database.requests.ParameterRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardParameterTypeEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AddCardParameterParser {
    private CardEntity cardEntity;
    private CardParameterTypeEntity cardParameterTypeEntity;

    public AddCardParameterParser(HttpServletRequest request) throws ServletException {
        long cardID = Long.parseLong(request.getParameter("cardID"));
        cardEntity = CardRequest.getCardByID(cardID);
        if (cardEntity != null) {
            long cardParameterTypeID = Long.parseLong(request.getParameter("cardParameterType"));
            cardParameterTypeEntity = ParameterRequest.getCardParameterType(cardParameterTypeID);
            if (cardParameterTypeEntity != null) {
                if (!cardParameterTypeEntity.isMultiply()) {
                    if (cardParameterTypeEntity.isTranslatable()) {
                        if (TextRequest.isCardTextExist(cardID, cardParameterTypeID)) {
                            throw new ServletException("text is already added to that card");
                        }
                    } else {
                        if (ParameterRequest.isCardParameterExist(cardID, cardParameterTypeID)) {
                            throw new ServletException("parameter is already added to that card");
                        }
                    }
                }
            } else {
                throw new ServletException("parameter type not found");
            }
        } else {
            throw new ServletException("card not found");
        }
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public CardParameterTypeEntity getCardParameterTypeEntity() {
        return cardParameterTypeEntity;
    }

    public void setCardParameterTypeEntity(CardParameterTypeEntity cardParameterTypeEntity) {
        this.cardParameterTypeEntity = cardParameterTypeEntity;
    }
}
