package model.phone.delayedtask;

import model.additionalentity.phone.MobileCardActivity;
import model.additionalentity.phone.MobileCardEvent;
import model.constants.Component;
import model.database.requests.StatisticRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;

import java.util.ArrayList;

public class AddCardEventsTask implements Runnable {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AddCardEventsTask.class);

    private final ArrayList<MobileCardEvent> mobileCardEvents;
    private final long userID;
    private final Thread thread;

    public AddCardEventsTask(ArrayList<MobileCardEvent> mobileCardEvents, long userID) {
        this.mobileCardEvents = mobileCardEvents;
        this.userID = userID;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            UserEntity userEntity = UserRequests.getUserByID(userID);
            long userContentID = userEntity.getUserContent().getUserContentID();
            StatisticRequest.addCardEvents(mobileCardEvents, userContentID);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void execute() {
        thread.start();
    }
}
