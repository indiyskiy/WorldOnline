package view.servlet.admin;

import controller.phone.parser.AllCardsParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.GetAllCardsHandler;
import model.phone.requesthandler.MobileHandler;
import view.servlet.phone.MobileServlet;

public class GetAllCardsServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, GetAllCardsServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new GetAllCardsHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllCardsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
