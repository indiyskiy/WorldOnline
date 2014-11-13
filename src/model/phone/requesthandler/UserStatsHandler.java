package model.phone.requesthandler;


import controller.parser.adminparser.AllCardParser;
import controller.phone.entity.MobileRequest;
import controller.phone.entity.UserStatsRequest;
import model.database.requests.CardRequest;
import model.database.requests.DishRequest;
import model.database.requests.MenuRequest;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.dishes.DishEntity;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.UserStatsEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class UserStatsHandler implements MobileHandler {

    public UserStatsEntity handleRequest(UserStatsRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        UserStatsEntity userStatsEntity = new UserStatsEntity();
        userStatsEntity.setUserGlobalUpdateStatus(UserDataRequest.getUserStatus(mobileRequest.getUserID()));
//        userStatsEntity.setCardCounter(CardRequest.countActiveCards());
//        userStatsEntity.setPricesCounter(DishRequest.countPrices());
//        userStatsEntity.setMenuCounter(MenuRequest.countMenus());
//        userStatsEntity.setNeedToCardsUpdate(CardRequest.isNeedToUpdate());
//        userStatsEntity.setNeedToPricesUpdate(DishEntity.isNeedToUpdate());
//        userStatsEntity.setUserEvents(UserDataRequest.getUserStatus());
        return userStatsEntity;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != UserStatsRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, UserStatsRequest.class);
        }
        UserStatsRequest userStatsRequest = (UserStatsRequest) mobileRequest;
        return handleRequest(userStatsRequest);

    }
}
