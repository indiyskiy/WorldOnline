package model.phone.delayedtask;

import helper.TimeManager;
import model.constants.Component;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.UserCardEntity;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;

public class AddSomeCardTask implements Runnable {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AddSomeCardTask.class);

    //    private final ArrayList<CardEntity> cardEntities;
    private final ArrayList<Long> cardIDs;
    private final UserEntity user;
    private final Thread thread;

//    public AddSomeCardTask(ArrayList<CardEntity> cardEntities, UserEntity user) {
//        this.cardEntities = cardEntities;
//        this.user = user;
//        this.thread = new Thread(this);
//    }

    public AddSomeCardTask(ArrayList<Long> cardIDs, UserEntity user) {
//        this.cardEntities = cardEntities;
        this.cardIDs = cardIDs;
        this.user = user;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        ArrayList<Object> cardsForUpdate = new ArrayList<>();
        ArrayList<Long> cardsForCreate = new ArrayList<>();

//        for (CardEntity cardEntity : cardEntities) {
        Timestamp currentTime = TimeManager.currentTime();
        for (long cardID : cardIDs) {
            UserCardEntity userCardEntity = UserDataRequest.getUserCard(user.getUserID(), cardID);
            if (userCardEntity == null) {
//                userCardEntity = new UserCardEntity();
//                userCardEntity.setCard(cardEntity);
//                userCardEntity.setDownloadTimeStamp(currentTime);
//                if (user.getUserContent() == null) {
//                    loggerFactory.error("user.getUserContent is null");
//                }
//                userCardEntity.setUserContent(user.getUserContent());
//                userCardEntity.setLastUpdateTimeStamp(currentTime);
//                try {
//                    UserDataRequest.addUserCard(userCardEntity);
//                } catch (InterruptedException e) {
//                    loggerFactory.error(e);
//                }
                cardsForCreate.add(cardID);
            } else {
                cardsForUpdate.add(cardID);
//                userCardEntity.setLastUpdateTimeStamp(currentTime);
//                UserDataRequest.updateUserCard(userCardEntity);
            }
        }
        UserDataRequest.updateUserCards(user, cardsForUpdate, currentTime);
        UserDataRequest.createUserCards(user, cardsForCreate, currentTime);
    }

    public void execute() {
        thread.start();
    }
}
