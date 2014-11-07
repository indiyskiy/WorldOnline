package model.phone.delayedtask;

import helper.TimeManager;
import model.constants.Component;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.UserPriceEntity;
import model.database.worldonlinedb.UserEntity;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.logger.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;

public class AddSomePricesTask implements Runnable {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AddSomePricesTask.class);

    private final ArrayList<PriceEntity> priceEntities;
    private final UserEntity user;
    private final Thread thread;

    public AddSomePricesTask(ArrayList<PriceEntity> priceEntities, UserEntity user) {
        this.priceEntities = priceEntities;
        this.user = user;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        loggerFactory.debug("AddSomePricesTask run");
        try {
            for (PriceEntity priceEntity : priceEntities) {
                UserPriceEntity userPriceEntity = UserDataRequest.getUserPrice(user.getUserID(), priceEntity.getPriceID());
                Timestamp currentTime = TimeManager.currentTime();
                if (userPriceEntity == null) {
                    userPriceEntity = new UserPriceEntity();
                    userPriceEntity.setPrice(priceEntity);
                    userPriceEntity.setDownloadTimeStamp(currentTime);
                    if (user.getUserContent() == null) {
                        loggerFactory.error("user.getUserContent is null");
                    }
                    userPriceEntity.setUserContent(user.getUserContent());
                    userPriceEntity.setLastUpdateTimeStamp(currentTime);
                    UserDataRequest.addUserPrice(userPriceEntity);
                    loggerFactory.debug("AddSomePricesTask addUserPrice");
                } else {
                    userPriceEntity.setLastUpdateTimeStamp(currentTime);
                    UserDataRequest.updateUserPrice(userPriceEntity);
                    loggerFactory.debug("AddSomePricesTask updateUserPrice");
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void execute() {
        thread.start();
    }
}
