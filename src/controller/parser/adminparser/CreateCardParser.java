package controller.parser.adminparser;

import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;
import model.database.worldonlinedb.CardEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class CreateCardParser {
    private CardEntity card;

    public void parse(HttpServletRequest request) throws ServletException {
        CardType cardType;
        try {
            cardType = CardType.parseInt(Integer.parseInt(request.getParameter("cardType")));
        } catch (Exception e) {
            throw new ServletException("incorrect card type");
        }
        String cardName = request.getParameter("cardName");
        if (cardName == null || cardName.isEmpty()) {
            throw new ServletException("card name is empty");
        }
        CardState cardState = CardState.NotActive;
        card = new CardEntity(cardType, cardName, cardState);
    }

    public CardEntity getCard() {
        return card;
    }
}
