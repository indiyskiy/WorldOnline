package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.CardRequest;
import model.database.requests.DishRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.database.worldonlinedb.dishes.CardPriceLinkEntity;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewPriceServlet extends ProtectedServlet {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddNewPriceServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            CardEntity cardEntity = CardRequest.getCardByID(cardID);
            PriceEntity priceEntity = new PriceEntity();
            TextGroupEntity textGroupEntity = new TextGroupEntity(cardEntity.getCardName() + "Price");
            TextEntity textRu = new TextEntity(LanguageType.Russian, "Прайс", textGroupEntity);
            TextEntity textEn = new TextEntity(LanguageType.Russian, "Price", textGroupEntity);
            TextRequest.addText(textRu);
            TextRequest.addText(textEn);
            priceEntity.setPriceName(textGroupEntity);
            DishRequest.addPrice(priceEntity);
            CardPriceLinkEntity cardPriceLinkEntity = new CardPriceLinkEntity();
            cardPriceLinkEntity.setCard(cardEntity);
            cardPriceLinkEntity.setPrice(priceEntity);
            DishRequest.addCardPriceLink(cardPriceLinkEntity);
            DishRequest.updatePrice(priceEntity);
            ServletHelper.sendForward("/completepriceinfo?priceID=" + priceEntity.getPriceID(), this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
