package model.scheduler;

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
