package model;

import model.database.requests.AdminUserRequest;
import model.database.requests.CardRequest;
import model.scheduler.Scheduler;
import test.TestStatistics;

public class ServerInit {
    private static ServerInit ourInstance = new ServerInit();

    public static ServerInit getInstance() {
        return ourInstance;
    }

    private ServerInit() {
        CardRequest.countCard();
        AdminUserRequest.addRootAdminUser();
        Scheduler.start();
//        TestStatistics.createCardActivities();
    }
}
