package controller.phone.parser;

import controller.phone.entity.CardRegistrationRequest;
import controller.phone.entity.MobileRequest;
import model.constants.Component;
import model.constants.ExceptionTexts;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class CardRegistrationParser extends MobileParser {

    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardRegistrationParser.class);

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        try {
            CardRegistrationRequest cardRegistrationRequest = new CardRegistrationRequest();
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
            String ids = body.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\\"", "").replaceAll(":", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").replaceAll("cardIDs", "");
            if (!ids.isEmpty()) {
                String[] cardIds = ids.split(",");
                for (String cardIDString : cardIds) {
                    Long cardID = Long.parseLong(cardIDString);
                    cardIDs.add(cardID);
                }
                cardRegistrationRequest.setCardIDs(cardIDs);
            }
            return cardRegistrationRequest;
        } catch (ParseRequestException e) {
            loggerFactory.error(e);
            throw e;
        } catch (Exception e) {
            loggerFactory.error(e);
            throw new ParseRequestException(e.getMessage());
        }
    }
}
