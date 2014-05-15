package model.phone.requesthandler;

import controller.phone.entity.GetAllMenuIDsRequest;
import controller.phone.entity.GetAllMenusRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.MenuCompleteInformation;
import model.database.requests.MenuRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.GetAllMenusResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.util.ArrayList;

/**
 * Created by Илья on 15.04.14.
 */
public class GetAllMenusHandler implements MobileHandler {
    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != GetAllMenusRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, GetAllMenuIDsRequest.class);
        }
        GetAllMenusRequest getAllMenuIDsRequest = (GetAllMenusRequest) mobileRequest;
        return handleRequest(getAllMenuIDsRequest);
    }

    public GetAllMenusResponse handleRequest(GetAllMenusRequest getAllMenusRequest) throws IllegalTypeException {
        GetAllMenusResponse getAllMenusResponse = new GetAllMenusResponse();
        ArrayList<MenuCompleteInformation> menusCompleteInformation = MenuRequest.getAllMenusCompleteInformation(getAllMenusRequest);
        getAllMenusResponse.setMenusCompleteInformation(menusCompleteInformation);
        return getAllMenusResponse;
    }
}

