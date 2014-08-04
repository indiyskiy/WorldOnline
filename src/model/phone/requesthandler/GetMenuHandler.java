package model.phone.requesthandler;

import controller.phone.entity.MenuRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.MenuCompleteInformation;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MenuResponse;
import model.phone.responseentity.MobileResponseEntity;

public class GetMenuHandler implements MobileHandler {

    public MenuResponse handleRequest(MenuRequest menuRequest) {
        MenuResponse menuResponse = new MenuResponse();
        MenuCompleteInformation menuCompleteInformation = model.database.requests.MenuRequest.getMenuCompleteInformation(menuRequest);
        menuResponse.setMenuCompleteInformation(menuCompleteInformation);
        return menuResponse;
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
