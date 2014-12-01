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

    private final ArrayList<Long> cardIDs;
    private final UserEntity user;
    private final Thread thread;


    public AddSomeCardTask(ArrayList<Long> cardIDs, UserEntity user) {
        this.cardIDs = cardIDs;
        this.user = user;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            if (cardIDs == null || cardIDs.isEmpty()) {
                return;
            }
            ArrayList<Object> cardsForUpdate = new ArrayList<>();
            ArrayList<Long> cardsForCreate = new ArrayList<>();
            Timestamp currentTime = TimeManager.currentTime();
            for (long cardID : cardIDs) {
                UserCardEntity userCardEntity = UserDataRequest.getUserCard(user.getUserID(), cardID);
                if (userCardEntity == null) {
                    cardsForCreate.add(cardID);
                } else {
                    cardsForUpdate.add(cardID);
                }
            }
            UserDataRequest.updateUserCards(user, cardsForUpdate, currentTime);
            UserDataRequest.createUserCards(user, cardsForCreate, currentTime);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void execute() {
        thread.start();
    }
}
