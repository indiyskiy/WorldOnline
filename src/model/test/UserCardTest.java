package model.test;

import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.UserCardEntity;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class UserCardTest {
    private static Random random = new Random(System.currentTimeMillis());
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Test, UserCardTest.class);

    public static boolean test() {
        try {
            CardRequest.deleteAllCardUsers();
            UserEntity userEntity = getRandomUser();
            ultimateTestSinc(userEntity);
            HashSet<CardEntity> cardEntities = getRandomCards(CardRequest.countCard() / 2);
            System.out.println(CardRequest.countCard() + " " + cardEntities.size());
            ultimateTestCardsUpdate(cardEntities);
            ArrayList<Long> actualCards = CardRequest.getActualCards(userEntity.getUserID());
            ArrayList<Long> unActualCards = CardRequest.getUnActualCards(userEntity.getUserID());
            System.out.println((actualCards.size() + unActualCards.size()) + " " + unActualCards.size());
    /*    loggerFactory.info("Test UserCardTest." +
                "\r\n actual size " + actualCards.size() +
                "\r\n unactual size " + unActualCards.size() +
                "\r\n " + cardEntities.size());
        if (CardRequest.countCard() != actualCards.size() + unActualCards.size()) {
            return false;
        }*/
            ArrayList<Long> cardsStatusChanged = CardRequest.getCardsStatusChanged(userEntity.getUserID());
            ArrayList<Long> cardsToUpdate = CardRequest.getCardsToUpdate(userEntity.getUserID());
            ArrayList<Long> cardsToAdd = CardRequest.getCardsToAdd(userEntity.getUserID());
     /*   loggerFactory.info(cardsStatusChanged.toString());
        loggerFactory.info(cardsToUpdate.toString());
        loggerFactory.info(cardsToAdd.toString());*/
            loggerFactory.info("StatusChanged " + cardsStatusChanged.size() + "\r\n" +
                    "Update " + cardsToUpdate.size() + "\r\n" +
                    "Add " + cardsToAdd.size() + "\r\n" +
                    "SUM " + (cardsStatusChanged.size() + cardsToUpdate.size() + cardsToAdd.size()));
            return true;
        } catch (Exception e) {
            loggerFactory.error(e);
            return false;
        }
    }

    private static void ultimateTestCardsUpdate(HashSet<CardEntity> cardEntities) {
        System.out.println("increment " + cardEntities.size() + " values");
        for (CardEntity cardEntity : cardEntities) {
            cardEntity.incrementCardVersion();
        }
        CardRequest.editCards(cardEntities);
    }

    private static HashSet<CardEntity> getRandomCards(long counter) {
        System.out.println("counter " + counter);
        HashSet<CardEntity> cardEntitySet = new HashSet<CardEntity>();
        ArrayList<CardEntity> cardEntities = CardRequest.getAllCards();
        for (long i = 0L; i < counter; i++) {
            CardEntity randomCard = cardEntities.get(random.nextInt(cardEntities.size()));
            cardEntitySet.add(randomCard);
        }
        System.out.println("cardEntitySet " + cardEntitySet.size());
        return cardEntitySet;
    }

    private static void ultimateTestSinc(UserEntity userEntity) {
        ArrayList<CardEntity> cardEntities = CardRequest.getAllCards();
        for (CardEntity cardEntity : cardEntities) {
            UserCardEntity userCardEntity = new UserCardEntity(userEntity, cardEntity);
            UserRequests.addUserCard(userCardEntity);
        }
        userEntity.updateLastSinc();
        UserRequests.editUser(userEntity);
    }

    private static UserEntity getRandomUser() {
        ArrayList<UserEntity> userEntities = UserRequests.getAllUsers();
        return userEntities.get(random.nextInt(userEntities.size()));
    }
}
