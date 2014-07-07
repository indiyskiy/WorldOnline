package model.phone.requesthandler;

import controller.phone.entity.MenuRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.MenuCompleteInformation;
import model.exception.IllegalTypeException;
import model.phone.responseentity.GetMenuResponse;
import model.phone.responseentity.MobileResponseEntity;

public class GetMenuHandler implements MobileHandler {

    public GetMenuResponse handleRequest(MenuRequest menuRequest) {
        GetMenuResponse getMenuResponse = new GetMenuResponse();
        MenuCompleteInformation menuCompleteInformation = model.database.requests.MenuRequest.getMenuCompleteInformation(menuRequest);
        getMenuResponse.setMenuCompleteInformation(menuCompleteInformation);
        return getMenuResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != MenuRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, MenuRequest.class);
        }
        MenuRequest menuRequest = (MenuRequest) mobileRequest;
        return handleRequest(menuRequest);
    }
}
