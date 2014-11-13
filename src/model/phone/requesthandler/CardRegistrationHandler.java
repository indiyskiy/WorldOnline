package model.phone.requesthandler;


import controller.phone.entity.CardRegistrationRequest;
import controller.phone.entity.MobileRequest;
import model.constants.Component;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.exception.IllegalTypeException;
import model.logger.LoggerFactory;
import model.phone.delayedtask.AddSomeCardTask;
import model.phone.responseentity.CardRegistrationEntity;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class CardRegistrationHandler implements MobileHandler {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardRegistrationHandler.class);

    public CardRegistrationEntity handleRequest(CardRegistrationRequest cardRegistrationRequest) {
        UserEntity userEntity = UserRequests.getUserByID(cardRegistrationRequest.getUserID());
        if (userEntity.getUserContent() == null) {
//            loggerFactory.debug("handleRequest- user content is null");
        }
        CardRegistrationEntity cardRegistrationEntity = new CardRegistrationEntity();
//        AddSomeCardTask addSomeCardTask = new AddSomeCardTask(cardRegistrationRequest.getCardEntities(), userEntity);
        AddSomeCardTask addSomeCardTask = new AddSomeCardTask(cardRegistrationRequest.getCardIDs(), userEntity);
        addSomeCardTask.execute();
        return cardRegistrationEntity;
    }


    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != CardRegistrationRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, CardRegistrationRequest.class);
        }
        CardRegistrationRequest cardRegistrationRequest = (CardRegistrationRequest) mobileRequest;
        return handleRequest(cardRegistrationRequest);
    }
}
