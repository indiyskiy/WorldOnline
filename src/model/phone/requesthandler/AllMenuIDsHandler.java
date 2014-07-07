package model.phone.requesthandler;

import controller.phone.entity.AllMenuIDsRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.MenuRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllMenuIDsResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.util.LinkedList;

public class AllMenuIDsHandler implements MobileHandler {
    public AllMenuIDsResponse handleRequest(AllMenuIDsRequest allMenuIDsRequest) {
        AllMenuIDsResponse allMenuIDsResponse = new AllMenuIDsResponse();
        LinkedList<Long> menuIDs = MenuRequest.getMenuList();
        allMenuIDsResponse.setMenuIDs(menuIDs);
        return allMenuIDsResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != AllMenuIDsRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllMenuIDsRequest.class);
        }
        AllMenuIDsRequest allMenuIDsRequest = (AllMenuIDsRequest) mobileRequest;
        return handleRequest(allMenuIDsRequest);
    }
}
