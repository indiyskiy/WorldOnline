package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import controller.phone.entity.PricesRegistrationRequest;
import controller.phone.parser.PricesRegistrationParser;
import model.constants.Component;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.exception.IllegalTypeException;
import model.logger.LoggerFactory;
import model.phone.delayedtask.AddSomePricesTask;
import model.phone.responseentity.CardRegistrationEntity;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class PricesRegistrationHandler implements MobileHandler {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, PricesRegistrationParser.class);

    public CardRegistrationEntity handleRequest(PricesRegistrationRequest pricesRegistrationRequest) {
        loggerFactory.debug("request handle");
        UserEntity userEntity = UserRequests.getUserByID(pricesRegistrationRequest.getUserID());
        CardRegistrationEntity cardRegistrationEntity = new CardRegistrationEntity();
        AddSomePricesTask addSomePricesTask = new AddSomePricesTask(pricesRegistrationRequest.getPricesIDs(), userEntity);
        addSomePricesTask.execute();
        loggerFactory.debug("request handled");
        return cardRegistrationEntity;
    }


    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != PricesRegistrationRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, PricesRegistrationRequest.class);
        }
        PricesRegistrationRequest pricesRegistrationRequest = (PricesRegistrationRequest) mobileRequest;
        return handleRequest(pricesRegistrationRequest);
    }
}
