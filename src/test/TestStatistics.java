package test;

import model.additionalentity.admin.SimpleDate;
import model.additionalentity.phone.MobileCardActivity;
import model.constants.Month;
import model.constants.databaseenumeration.CardState;
import model.database.requests.CardRequest;
import model.database.requests.StatisticRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.UserEntity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TestStatistics {
    public static void createCardActivities() {
        if (!StatisticRequest.isCardActivitiesTableEmpty()) {
            return;
        }
        final long halfHour = 1800000;
        final long day = 86400000;
        final long minute = 60000;
        SimpleDate simpleDate = new SimpleDate(2014, Month.December, 1, 10, 30);
        final long startMills = simpleDate.getTimestamp().getTime();
        //beginning day, skip cards, card time
        final int[][] data = new int[][]{{0, 3, 1}, {4, 5, 3}, {5, 7, 5}};
        ArrayList<UserEntity> users = UserRequests.getAllUsers(0, data.length);
        for (int i = 0; i < users.size(); i++) {
            UserEntity user = users.get(i);
            long userContentID = user.getUserContent().getUserContentID();
            ArrayList<CardEntity> cards = CardRequest.getAllCards();
            ArrayList<MobileCardActivity> mobileCardActivities = new ArrayList<>();
            long userMills = startMills + day * data[i][0];
            for (int j = 0; j < cards.size(); j += data[i][1]) {
                CardEntity card = cards.get(j);
                if (card.getCardState() == CardState.Active.getValue()) {
                    long cardID = card.getCardID();
                    userMills += halfHour;
                    Timestamp onTimestamp = new Timestamp(userMills);
                    userMills += minute * data[i][2];
                    Timestamp offTimestamp = new Timestamp(userMills);
                    MobileCardActivity mobileCardActivity = new MobileCardActivity(cardID, onTimestamp, offTimestamp);
                    mobileCardActivities.add(mobileCardActivity);
                }
            }
            StatisticRequest.addCardActivities(mobileCardActivities, userContentID);
        }
    }

}
