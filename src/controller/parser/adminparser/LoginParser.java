package controller.parser.adminparser;

import model.additionalentity.admin.LoginRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoginParser {
    public LoginRequest parse(HttpServletRequest request) throws ServletException {
        LoginRequest loginRequest = new LoginRequest();
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
