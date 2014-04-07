package model.logger;

import model.constants.Component;
import model.exception.ExceptionUtils;

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
        error(ExceptionUtils.getTrace(error));
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
