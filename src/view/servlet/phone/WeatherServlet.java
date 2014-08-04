package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.WeatherParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.WeatherHandler;

public class WeatherServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, MobileServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new WeatherHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new WeatherParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
