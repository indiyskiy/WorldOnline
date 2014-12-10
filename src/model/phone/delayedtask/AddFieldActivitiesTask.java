package model.phone.delayedtask;

import model.additionalentity.phone.MobileCardEvent;
import model.additionalentity.phone.MobileFieldActivity;
import model.constants.Component;
import model.database.requests.StatisticRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;

import java.util.ArrayList;

public class AddFieldActivitiesTask implements Runnable {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AddFieldActivitiesTask.class);

    private final ArrayList<MobileFieldActivity> mobileFieldActivities;
    private final long userID;
    private final Thread thread;

    public AddFieldActivitiesTask(ArrayList<MobileFieldActivity> mobileFieldActivities, long userID) {
        this.mobileFieldActivities = mobileFieldActivities;
        this.userID = userID;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            UserEntity userEntity = UserRequests.getUserByID(userID);
            long userContentID = userEntity.getUserContent().getUserContentID();
            StatisticRequest.addFieldActivities(mobileFieldActivities, userContentID);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void execute() {
        thread.start();
    }
}
