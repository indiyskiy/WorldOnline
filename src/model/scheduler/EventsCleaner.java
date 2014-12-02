package model.scheduler;


import model.database.requests.TimeRequest;

public class EventsCleaner extends ExecutableTask {

    public EventsCleaner() {
        super(12);
    }

    @Override
    public void execute() {
        TimeRequest.disableOldEvents();
        TimeRequest.deleteElderEvents();
    }
}
