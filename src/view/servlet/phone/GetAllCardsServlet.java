package view.servlet.phone;

import controller.phone.parser.GetAllCardsParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.GetAllCardsHandler;
import model.phone.requesthandler.MobileHandler;

public class GetAllCardsServlet extends MobileServlet {

    @Override
    protected MobileHandler getMobileHandler() {
        return new GetAllCardsHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new GetAllCardsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return new LoggerFactory(Component.Phone, GetAllCardsServlet.class);
    }
}
