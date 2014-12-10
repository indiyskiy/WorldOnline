package model.phone.requesthandler;

import controller.phone.entity.CardActivityRegistrationRequest;
import controller.phone.entity.CardEventRegistrationRequest;
import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.phone.delayedtask.AddCardActivitiesTask;
import model.phone.delayedtask.AddCardEventsTask;
import model.phone.responseentity.CardActivityRegistrationResponse;
import model.phone.responseentity.CardEventRegistrationResponse;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.SimpleResponse;

public class CardEventRegistrationHandler implements MobileHandler {
    public SimpleResponse handleRequest(CardEventRegistrationRequest cardEventRegistrationRequest) {
        AddCardEventsTask addCardEventsTask =
                new AddCardEventsTask(cardEventRegistrationRequest.getMobileCardEvents(),
                        cardEventRegistrationRequest.getUserID());
        addCardEventsTask.execute();
        return new SimpleResponse();
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != CardEventRegistrationRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, CardEventRegistrationRequest.class);
        }
        CardEventRegistrationRequest cardActivityRegistrationRequest = (CardEventRegistrationRequest) mobileRequest;
        return handleRequest(cardActivityRegistrationRequest);
    }
}
