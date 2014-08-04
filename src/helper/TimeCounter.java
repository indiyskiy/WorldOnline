package helper;

import model.constants.Component;
import model.logger.LoggerFactory;

import java.sql.Timestamp;

public class TimeCounter {
    private static LoggerFactory logger = new LoggerFactory(Component.Global, TimeCounter.class);
    private Timestamp timestamp;
    private int unicID;
    private static int counter = 0;

    public TimeCounter() {
        timestamp = TimeManager.currentTime();
        unicID = counter;
        counter++;
    }

    public void logTime(LoggerFactory loggerFactory, String message) {
        try {
            Timestamp tmpStamp = TimeManager.currentTime();
            loggerFactory.debug("timeCounterInfo-" + unicID + ": " + (tmpStamp.getTime() - timestamp.getTime()) + " " + message);
            timestamp = tmpStamp;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
