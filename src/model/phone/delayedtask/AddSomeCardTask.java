package model.phone.delayedtask;

import helper.TimeManager;
import model.constants.Component;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.UserCardEntity;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;

public class AddSomeCardTask implements Runnable {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AddSomeCardTask.class);

    private final ArrayList<CardEntity> cardEntities;
    private final UserEntity user;
    private final Thread thread;

    public AddSomeCardTask(ArrayList<CardEntity> cardEntities, UserEntity user) {
        this.cardEntities = cardEntities;
        this.user = user;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        for (CardEntity cardEntity : cardEntities) {
            UserCardEntity userCardEntity = UserDataRequest.getUserCard(user.getUserID(), cardEntity.getCardID());
            Timestamp currentTime = TimeManager.currentTime();
            if (userCardEntity == null) {
                userCardEntity = new UserCardEntity();
                userCardEntity.setCard(cardEntity);
                userCardEntity.setDownloadTimeStamp(currentTime);
                if (user.getUserContent() == null) {
                    loggerFactory.error("user.getUserContent is null");
                }
                userCardEntity.setUserContent(user.getUserContent());
                userCardEntity.setLastUpdateTimeStamp(currentTime);
                UserDataRequest.addUserCard(userCardEntity);
            } else {
                userCardEntity.setLastUpdateTimeStamp(currentTime);
                UserDataRequest.updateUserCard(userCardEntity);
            }
        }
    }

    public void execute() {
        thread.start();
    }
}
