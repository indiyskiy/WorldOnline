package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.UserRegistrationParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.UserRegistrationHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class UserRegistrationServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new UserRegistrationHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new UserRegistrationParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        doPost(request, response);
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            ParsedRegistrationRequest parsedRegistrationRequest = UserRegistrationParser.parse(request);
//            RegistrationResponse registrationResponse = UserRegistrationHandler.handleRequest(parsedRegistrationRequest);
//            String responseString = UserRegistrationParser.getResponse(registrationResponse);
//            ServletHelper.sendJson(response, responseString);
//        } catch (Exception e) {
//            ServletHelper.sendMobileError(loggerFactory, e, response);
//        }
//    }

}