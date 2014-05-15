package model.scheduler;

/**
 * Created by Илья on 14.05.14.
 */
public interface Executable {
    int getPeriod();

    void execute();
}
