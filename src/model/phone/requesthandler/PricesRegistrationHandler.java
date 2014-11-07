package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import controller.phone.entity.PricesRegistrationRequest;
import model.constants.Component;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.exception.IllegalTypeException;
import model.logger.LoggerFactory;
import model.phone.delayedtask.AddSomePricesTask;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.PricesRegistrationEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class PricesRegistrationHandler implements MobileHandler {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, PricesRegistrationHandler.class);

    public PricesRegistrationEntity handleRequest(PricesRegistrationRequest priceRegistrationRequest) {
        UserEntity userEntity = UserRequests.getUserByID(priceRegistrationRequest.getUserID());
        if (userEntity.getUserContent() == null) {
            loggerFactory.debug("handleRequest- user content is null");
        }
        PricesRegistrationEntity priceRegistrationEntity = new PricesRegistrationEntity();
        loggerFactory.debug("add addSomePriceTask " + priceRegistrationRequest.getPriceEntities().size());
        AddSomePricesTask addSomePriceTask = new AddSomePricesTask(priceRegistrationRequest.getPriceEntities(), userEntity);
        addSomePriceTask.execute();
        return priceRegistrationEntity;
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
