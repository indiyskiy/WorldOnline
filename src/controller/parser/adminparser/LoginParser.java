package controller.parser.adminparser;

import model.additionalentity.LoginRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 31.03.14.
 */
public class LoginParser {
    public LoginRequest parse(HttpServletRequest request) throws ServletException {
        LoginRequest loginRequest = new LoginRequest();
        if (request.getParameter("login") != null && !request.getParameter("login").equals("")) {
            throw new ServletException("The try of autologining detected");
        }
        String login = request.getParameter("firstField");
        String password = request.getParameter("secondField");
        if (login == null || password == null || login.equals("") || password.equals("")) {
            throw new ServletException("Enter login and password");
        }
        loginRequest.setLogin(login);
        loginRequest.setPassword(password);
        return loginRequest;
    }
}
