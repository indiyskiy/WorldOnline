package controller.phone.parser;


import controller.phone.entity.MobileRequest;
import controller.phone.entity.PricesRegistrationRequest;
import model.constants.ExceptionTexts;
import model.database.requests.DishRequest;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.exception.ParseRequestException;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class PricesRegistrationParser extends MobileParser {
    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        try {
            PricesRegistrationRequest pricesRegistrationRequest = new PricesRegistrationRequest();
            ArrayList<PriceEntity> priceEntities = new ArrayList<>();
            ArrayList<Long> priceIDs = new ArrayList<>();
            try {
                pricesRegistrationRequest.setUserID(Long.parseLong(request.getParameter("userID")));
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.pricesRegistrationUserIDIncorrectException);
            }
            String body = ServletHelper.getBody(request);
            if (body == null || body.isEmpty()) {
                throw new ParseRequestException(ExceptionTexts.pricesRegistrationBodyEmptyException);
            }
            String ids = body.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\\"", "").replaceAll(":", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").replaceAll("pricesIDs", "");
            String[] priceIds = ids.split(",");
            for (String priceIDString : priceIds) {
                Long priceID = Long.parseLong(priceIDString);
                priceIDs.add(priceID);
                PriceEntity priceEntity = DishRequest.getPrice(priceID);
                if (priceEntity != null) {
                    priceEntities.add(priceEntity);
                }
            }
            if (priceIDs.size() != priceEntities.size()) {
                throw new ParseRequestException(ExceptionTexts.pricesRegistrationPriceListEmptyException);
            }
            pricesRegistrationRequest.setPriceEntities(priceEntities);
            return pricesRegistrationRequest;
        } catch (ParseRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseRequestException(e.getMessage());
        }
    }
}
