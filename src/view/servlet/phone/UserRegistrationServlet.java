package view.servlet.phone;

import controller.phone.entity.ParsedRegistrationRequest;
import controller.phone.parser.RegistrationParser;
import model.constants.Component;
import model.constants.ProtectAdminLevel;
import model.logger.LoggerFactory;
import model.phone.requesthandler.RegistrationHandler;
import model.phone.responseentity.RegistrationResponse;
import view.servlet.ServletHelper;
import view.servlet.admin.ProtectedServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class UserRegistrationServlet extends HttpServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserRegistrationServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        loggerFactory.debug("UserRegistrationServlet started");
        try {
            ParsedRegistrationRequest parsedRegistrationRequest = RegistrationParser.parse(request);
            RegistrationResponse registrationResponse = RegistrationHandler.handleRequest(parsedRegistrationRequest);
            String responseString = RegistrationParser.getResponse(registrationResponse);
            ServletHelper.sendResponse(response, responseString);
        } catch (Exception e) {
            ServletHelper.sendMobileError(loggerFactory, e, response);
        }
    }

}