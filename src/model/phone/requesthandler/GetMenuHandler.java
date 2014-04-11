package model.phone.requesthandler;

import controller.phone.entity.GetMenuRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.MenuCompleteInformation;
import model.database.requests.MenuRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.GetMenuResponse;
import model.phone.responseentity.MobileResponseEntity;

public class GetMenuHandler implements MobileHandler {

    public GetMenuResponse handleRequest(GetMenuRequest getMenuRequest) {
        GetMenuResponse getMenuResponse = new GetMenuResponse();
        MenuCompleteInformation menuCompleteInformation = MenuRequest.getMenuCompleteInformation(getMenuRequest);
        getMenuResponse.setMenuCompleteInformation(menuCompleteInformation);
        return getMenuResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != GetMenuRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, GetMenuRequest.class);
        }
        GetMenuRequest getMenuRequest = (GetMenuRequest) mobileRequest;
        return handleRequest(getMenuRequest);
    }
}
