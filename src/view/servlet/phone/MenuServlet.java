package view.servlet.phone;

import controller.phone.parser.GetMenuParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.GetMenuHandler;
import model.phone.requesthandler.MobileHandler;

/**
 * Created by Илья on 10.04.14.
 */
public class MenuServlet extends MobileServlet {
    @Override
    protected MobileHandler getMobileHandler() {
        return new GetMenuHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new GetMenuParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return new LoggerFactory(Component.Phone, MenuServlet.class);
    }

}
