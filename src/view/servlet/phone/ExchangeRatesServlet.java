package view.servlet.phone;


import controller.phone.parser.ExchangeRatesParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.ExchangeRatesHandler;
import model.phone.requesthandler.MobileHandler;

public class ExchangeRatesServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, MobileServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new ExchangeRatesHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new ExchangeRatesParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
