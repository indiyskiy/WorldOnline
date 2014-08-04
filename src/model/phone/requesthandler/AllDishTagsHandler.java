package model.phone.requesthandler;


import controller.phone.entity.AllDishTagsRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.DishRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllDishTagsResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class AllDishTagsHandler implements MobileHandler {

    public AllDishTagsResponse handleRequest(AllDishTagsRequest allDishTagsRequest) throws IllegalTypeException, ServletException, SQLException {
        AllDishTagsResponse allDishTagsResponse = new AllDishTagsResponse();
        allDishTagsResponse.setAllDishTags(DishRequest.getAllMobileDishTags(allDishTagsRequest.getUserID()));
        return allDishTagsResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != AllDishTagsRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllDishTagsRequest.class);
        }
        AllDishTagsRequest allDishTagsRequest = (AllDishTagsRequest) mobileRequest;
        return handleRequest(allDishTagsRequest);
    }
}
