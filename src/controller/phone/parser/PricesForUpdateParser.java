package controller.phone.parser;


import controller.phone.entity.MobileRequest;
import controller.phone.entity.PricesForUpdateRequest;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PricesForUpdateParser extends MobileParser {
    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        PricesForUpdateRequest pricesForUpdateRequest = new PricesForUpdateRequest();
        pricesForUpdateRequest.setUserID(Long.parseLong(request.getParameter("userID")));
        return pricesForUpdateRequest;
    }
}
