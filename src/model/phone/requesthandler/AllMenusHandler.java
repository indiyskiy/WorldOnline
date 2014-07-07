package model.phone.requesthandler;

import controller.phone.entity.AllMenuIDsRequest;
import controller.phone.entity.AllMenusRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.MenuCompleteInformation;
import model.database.requests.MenuRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllMenusResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.util.ArrayList;

public class AllMenusHandler implements MobileHandler {
    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != AllMenusRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllMenuIDsRequest.class);
        }
        AllMenusRequest getAllMenusRequest = (AllMenusRequest) mobileRequest;
        return handleRequest(getAllMenusRequest);
    }

    public AllMenusResponse handleRequest(AllMenusRequest allMenusRequest) throws IllegalTypeException {
        AllMenusResponse allMenusResponse = new AllMenusResponse();
        ArrayList<MenuCompleteInformation> menusCompleteInformation = MenuRequest.getAllMenusCompleteInformation(allMenusRequest);
        allMenusResponse.setMenusCompleteInformation(menusCompleteInformation);
        return allMenusResponse;
    }
}

