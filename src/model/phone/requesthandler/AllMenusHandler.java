package model.phone.requesthandler;

import controller.phone.entity.GetAllMenuIDsRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.MenuRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllMenusResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.util.LinkedList;


public class AllMenusHandler implements MobileHandler {
    public AllMenusResponse handleRequest(GetAllMenuIDsRequest getAllMenuIDsRequest) {
        AllMenusResponse allMenusResponse = new AllMenusResponse();
        LinkedList<Long> menuIDs = MenuRequest.getMenuList();
        allMenusResponse.setMenuIDs(menuIDs);
        return allMenusResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != GetAllMenuIDsRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, GetAllMenuIDsRequest.class);
        }
        GetAllMenuIDsRequest getAllMenuIDsRequest = (GetAllMenuIDsRequest) mobileRequest;
        return handleRequest(getAllMenuIDsRequest);
    }
}
