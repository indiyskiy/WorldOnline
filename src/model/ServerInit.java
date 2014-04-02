package model;

import model.database.requests.AdminUserRequest;
import model.database.requests.CardRequest;

/**
 * Created by Илья on 01.04.14.
 */
public class ServerInit {
    private static ServerInit ourInstance = new ServerInit();

    public static ServerInit getInstance() {
        return ourInstance;
    }

    private ServerInit() {
        CardRequest.countCard();
        AdminUserRequest.addRootAdminUser();
    }
}
