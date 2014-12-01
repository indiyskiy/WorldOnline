package model.phone.delayedtask;

import helper.TimeManager;
import model.constants.Component;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.UserEntity;
import model.database.worldonlinedb.UserPriceEntity;
import model.logger.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;

public class AddSomePricesTask implements Runnable {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AddSomePricesTask.class);

    private final ArrayList<Long> priceIDs;
    private final UserEntity user;
    private final Thread thread;


    public AddSomePricesTask(ArrayList<Long> priceIDs, UserEntity user) {
        this.priceIDs = priceIDs;
        this.user = user;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            if (priceIDs == null || priceIDs.isEmpty()) {
                return;
            }
            ArrayList<Object> pricesForUpdate = new ArrayList<>();
            ArrayList<Long> pricesForCreate = new ArrayList<>();
            Timestamp currentTime = TimeManager.currentTime();
            for (long priceID : priceIDs) {
                UserPriceEntity userPriceEntity = UserDataRequest.getUserPrice(user.getUserID(), priceID);
                if (userPriceEntity == null) {
                    pricesForCreate.add(priceID);
                } else {
                    pricesForUpdate.add(priceID);
                }
            }
            UserDataRequest.updateUserPrices(user, pricesForUpdate, currentTime);
            UserDataRequest.createUserPrices(user, pricesForCreate, currentTime);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void execute() {
        thread.start();
    }
}
