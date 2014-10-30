package controller.phone.parser;

import controller.phone.entity.CardRegistrationRequest;
import controller.phone.entity.MobileRequest;
import model.constants.Component;
import model.constants.ExceptionTexts;
import model.database.requests.CardRequest;
import model.database.worldonlinedb.CardEntity;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class CardRegistrationParser extends MobileParser {

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        try {
            CardRegistrationRequest cardRegistrationRequest = new CardRegistrationRequest();
            ArrayList<CardEntity> cardEntities = new ArrayList<>();
            ArrayList<Long> cardIDs = new ArrayList<>();
            try {
                cardRegistrationRequest.setUserID(Long.parseLong(request.getParameter("userID")));
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.cardRegistrationUserIDIncorrectException);
            }
            String body = ServletHelper.getBody(request);
            if (body == null || body.isEmpty()) {
                throw new ParseRequestException(ExceptionTexts.cardRegistrationBodyEmptyException);
            }
            String ids = body.replaceAll("\\[", "").replaceAll("\\]", "");
            String[] cardIds = ids.split(",");
            for (String cardIDString : cardIds) {
                Long cardID = Long.parseLong(cardIDString);
                cardIDs.add(cardID);
                CardEntity cardEntity = CardRequest.getCardByID(cardID);
                if (cardEntity != null) {
                    cardEntities.add(cardEntity);
                }
            }
            if (cardIDs.size() != cardEntities.size()) {
                throw new ParseRequestException(ExceptionTexts.cardRegistrationCardListEmptyException);
            }
            cardRegistrationRequest.setCardEntities(cardEntities);
            return cardRegistrationRequest;
        } catch (ParseRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseRequestException(e.getMessage());
        }
    }
}
