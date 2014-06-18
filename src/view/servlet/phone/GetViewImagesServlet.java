package view.servlet.phone;

import controller.phone.parser.GetViewImagesParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.GetViewImageHandler;
import model.phone.requesthandler.MobileHandler;

public class GetViewImagesServlet extends MobileServlet {
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, GetViewImagesServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new GetViewImageHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new GetViewImagesParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
