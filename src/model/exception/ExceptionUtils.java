package model.exception;

import model.constants.Component;
import model.logger.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 26.12.13
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionUtils {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, ExceptionUtils.class);

    public static String getString(Exception exception) {
        try {
            String exceptionString = exception.toString();
            StackTraceElement[] stackTrace = exception.getStackTrace();
            String completeTrace = "";
            for (StackTraceElement aStackTrace : stackTrace) {
                if (completeTrace.equals(""))
                    completeTrace = exceptionString + "\n\t" + aStackTrace.toString() + "\n";
                else
                    completeTrace = completeTrace + "\t" + aStackTrace + "\n";
            }

            return completeTrace;
        } catch (Exception e) {
            loggerFactory.error(e.toString());
            return "";
        }
    }

    public static String getTrace(Exception e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        return writer.toString();
    }
}