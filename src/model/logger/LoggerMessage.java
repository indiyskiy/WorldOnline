package model.logger;

import model.constants.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 25.12.13
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class LoggerMessage {
    private final static LogLevel lowestMailLevel = LogLevel.Warning;
    private final String text;
    private final LogLevel logLevel;
    private final String timestamp;
    private final String date;
    private final Component component;
    private final String callerClass;

    /*public LoggerMessage(String text) {
        this.text = text;
        this.logLevel=LogLevel.Default;
        this.component=Component.Global;
        timestamp =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        date =  new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        callerClass="unknown";
    }*/

    public LoggerMessage(String text, LogLevel logLevel, Component component, Class callerClass) {
        this.text = text;
        this.logLevel = logLevel;
        this.component = component;
        timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        this.callerClass = callerClass.getCanonicalName();
    }

    public String getText() {
        return text;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return date;
    }

    public Component getComponent() {
        return component;
    }

    public String getMessage() {
        return timestamp + " " + logLevel.toString().toUpperCase() + " " + callerClass + ": " + text;
    }

    public void logMyself(LoggerFile loggerFile) {
        loggerFile.printToFile(getMessage());
        System.out.println(getMessage());
        System.out.println("ErrorMailer " + logLevel + " " + lowestMailLevel);
        if (logLevel.isBigger(lowestMailLevel)) {
//            ErrorMailer.sendError(this);
        }
    }
}
