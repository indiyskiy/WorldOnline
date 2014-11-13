package controller.phone.parser;

import controller.phone.entity.UserGlobalUpdateRequest;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserGlobalUpdateParser extends MobileParser {
    @Override
    public UserGlobalUpdateRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        UserGlobalUpdateRequest userGlobalUpdateRequest = new UserGlobalUpdateRequest();
//        userGlobalUpdateRequest.setUserID(Long.parseLong(request.getParameter());
        return userGlobalUpdateRequest;
    }
}
