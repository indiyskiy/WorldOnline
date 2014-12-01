package controller.phone.parser;


import controller.phone.entity.MobileRequest;
import controller.phone.entity.PricesRegistrationRequest;
import model.constants.Component;
import model.constants.ExceptionTexts;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class PricesRegistrationParser extends MobileParser {
    private final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, PricesRegistrationParser.class);

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        try {
            PricesRegistrationRequest priceRegistrationRequest = new PricesRegistrationRequest();
            ArrayList<Long> priceIDs = new ArrayList<>();
            try {
                priceRegistrationRequest.setUserID(Long.parseLong(request.getParameter("userID")));
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.pricesRegistrationUserIDIncorrectException);
            }
            String body = ServletHelper.getBody(request);
            if (body == null || body.isEmpty()) {
                throw new ParseRequestException(ExceptionTexts.pricesRegistrationBodyEmptyException);
            }
            String ids = body.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\\"", "").replaceAll(":", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").replaceAll("pricesIDs", "");
            if (!ids.isEmpty()) {
                String[] priceIds = ids.split(",");
                for (String priceIDString : priceIds) {
                    Long priceID = Long.parseLong(priceIDString);
                    priceIDs.add(priceID);
                }
                priceRegistrationRequest.setPricesIDs(priceIDs);
            }
            return priceRegistrationRequest;
        } catch (ParseRequestException e) {
            loggerFactory.error(e);
            throw e;
        } catch (Exception e) {
            loggerFactory.error(e);
            throw new ParseRequestException(e.getMessage());
        }
    }
}