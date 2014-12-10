package model.phone.requesthandler;

import controller.phone.entity.CardActivityRegistrationRequest;
import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.phone.delayedtask.AddCardActivitiesTask;
import model.phone.responseentity.CardActivityRegistrationResponse;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.SimpleResponse;

public class CardActivityRegistrationHandler implements MobileHandler {
    public SimpleResponse handleRequest(CardActivityRegistrationRequest cardActivityRegistrationRequest) {
        AddCardActivitiesTask addCardActivitiesTask =
                new AddCardActivitiesTask(cardActivityRegistrationRequest.getMobileCardActivities(),
                        cardActivityRegistrationRequest.getUserID());
        addCardActivitiesTask.execute();
        return new SimpleResponse();
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != CardActivityRegistrationRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, CardActivityRegistrationRequest.class);
        }
        CardActivityRegistrationRequest cardActivityRegistrationRequest = (CardActivityRegistrationRequest) mobileRequest;
        return handleRequest(cardActivityRegistrationRequest);
    }
}
