package controller.phone.parser;


import controller.phone.entity.CardByListRequest;
import controller.phone.entity.MobileRequest;
import model.constants.ExceptionTexts;
import model.exception.ParseRequestException;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class CardByListParser extends MobileParser {

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        try {
            CardByListRequest cardByListRequest = new CardByListRequest();
            try {
                cardByListRequest.setUserID(Long.parseLong(request.getParameter("userID")));
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.cardByListUserIDIncorrectException);
            }
            ArrayList<Object> cardIDList = new ArrayList<>();
            String body = ServletHelper.getBody(request);
            if (body == null || body.isEmpty()) {
                throw new ParseRequestException(ExceptionTexts.cardByListBodyEmptyException);
            }
            String ids = body.replaceAll("\\[", "").replaceAll("\\]", "");
            for (String idString : ids.split(",")) {
                cardIDList.add(Long.parseLong(idString));
            }
            if (cardIDList.isEmpty()) {
                throw new ParseRequestException(ExceptionTexts.cardByListCardListEmptyException);
            }
            cardByListRequest.setCardIDs(cardIDList);
            return cardByListRequest;
        } catch (ParseRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseRequestException(e.getMessage());
        }
    }
}
