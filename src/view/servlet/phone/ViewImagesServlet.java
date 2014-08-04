package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.ViewImagesParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.ViewImageHandler;

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
