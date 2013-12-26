package model.logger;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 25.12.13
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public enum LogLevel {
    Default(0),
    Debug(1),
    Info(2),
    Warning(3),
    Error(4);

    private final int value;

    private LogLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LogLevel parseInt(int value) {
        LogLevel[] changingTypes = LogLevel.values();
        if (value <= 0 || value >=changingTypes.length) {
            return Default;
        } else {
            return changingTypes[value];
        }
    }

    public boolean isBigger(LogLevel logLevel) {
        return (this.value>=logLevel.getValue());
    }
}
