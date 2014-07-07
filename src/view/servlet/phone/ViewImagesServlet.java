package view.servlet.phone;

import controller.phone.parser.ViewImagesParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.ViewImageHandler;
import model.phone.requesthandler.MobileHandler;

public class ViewImagesServlet extends MobileServlet {
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, ViewImagesServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new ViewImageHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new ViewImagesParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
