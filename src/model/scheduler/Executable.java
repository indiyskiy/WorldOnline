package model.scheduler;

public interface Executable {
    int getPeriod();

    void execute();
}
