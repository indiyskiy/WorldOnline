package model.phone.delayedtask;

import model.additionalentity.phone.MobileCardActivity;
import model.constants.Component;
import model.database.requests.StatisticRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;

import java.util.ArrayList;

public class AddCardActivitiesTask implements Runnable {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AddCardActivitiesTask.class);

    private final ArrayList<MobileCardActivity> mobileCardActivities;
    private final long userID;
    private final Thread thread;

    public AddCardActivitiesTask(ArrayList<MobileCardActivity> mobileCardActivities, long userID) {
        this.mobileCardActivities = mobileCardActivities;
        this.userID = userID;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            UserEntity userEntity = UserRequests.getUserByID(userID);
            long userContentID = userEntity.getUserContent().getUserContentID();
            StatisticRequest.addCardActivities(mobileCardActivities, userContentID);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void execute() {
        thread.start();
    }
}
