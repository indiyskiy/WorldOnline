package controller.parser.adminparser;

import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.MenuType;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.MenuEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class CreateCardParser {
    private CardEntity card;
    private MenuEntity menuEntity;

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
        String menu = request.getParameter("menuID");
        if (menu != null && !menu.isEmpty()) {
            Long menuID = Long.parseLong(menu);
            menuEntity = MenuRequest.getMenu(menuID);
            if (menuEntity == null) {
                throw new ServletException("menu id is incorrect");
            }
        }
    }

    public CardEntity getCard() {
        return card;
    }

    public MenuEntity getMenuEntity() {
        return menuEntity;
    }
}
