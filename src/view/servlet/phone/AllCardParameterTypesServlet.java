package view.servlet.phone;

import controller.phone.parser.AllCardParameterTypesParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllCardParameterTypesHandler;
import model.phone.requesthandler.MobileHandler;

public class AllCardParameterTypesServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllCardParameterTypesHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllCardParameterTypesParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }


}