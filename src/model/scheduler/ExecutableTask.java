package model.scheduler;

/**
 * Created by Илья on 14.05.14.
 */
public abstract class ExecutableTask implements Executable {
    private final int period;

    public ExecutableTask(int period) {
        this.period = period;
    }

    @Override
    public int getPeriod() {
        return period;
    }

    public abstract void execute();
}
