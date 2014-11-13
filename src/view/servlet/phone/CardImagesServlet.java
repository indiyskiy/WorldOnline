package view.servlet.phone;

import controller.phone.parser.CardImagesParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.CardImageHandler;
import model.phone.requesthandler.MobileHandler;

public class CardImagesServlet extends MobileServlet {
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardImagesServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new CardImageHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new CardImagesParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
