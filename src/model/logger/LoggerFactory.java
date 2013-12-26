package model.logger;

import model.constants.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 26.12.13
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public class LoggerFactory {
    private Component component;
    private Class senderClass;

    public LoggerFactory(Component component, Class senderClass) {
        this.component = component;
        this.senderClass = senderClass;
    }

    public void log(String message, LogLevel level) {
        Logger logger = Logger.getInstance();
        logger.addMessage(message, level, component, senderClass);
    }

    public void log(String message) {
        Logger logger = Logger.getInstance();
        logger.addMessage(message, LogLevel.Default, component, senderClass);
    }

    public void error(String message) {
        log(message, LogLevel.Error);
    }
    public void error(Exception error) {
        log(error.toString());
    }

    public void info(String message) {
        log(message, LogLevel.Info);
    }

    public void debug(String message) {
        log(message, LogLevel.Debug);
    }

    public void warning(String message) {
        log(message, LogLevel.Warning);
    }
}
