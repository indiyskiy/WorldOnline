package view.servlet.phone;


import controller.phone.parser.CardsForUpdateParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.CardsForUpdateHandler;
import model.phone.requesthandler.MobileHandler;

public class CardsForUpdateServlet extends MobileServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardsForUpdateServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new CardsForUpdateHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new CardsForUpdateParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
