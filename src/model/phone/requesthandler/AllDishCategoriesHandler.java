package model.phone.requesthandler;

import controller.phone.entity.AllDishCategoriesRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.DishRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllDishCategoriesResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class AllDishCategoriesHandler implements MobileHandler {

    public AllDishCategoriesResponse handleRequest(AllDishCategoriesRequest allDishCategoriesRequest) throws IllegalTypeException, ServletException, SQLException {
        AllDishCategoriesResponse allDishCategoriesResponse = new AllDishCategoriesResponse();
        allDishCategoriesResponse.setMobileDishCategories(DishRequest.getAllMobileDishCategories(allDishCategoriesRequest.getUserID()));
        return allDishCategoriesResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != AllDishCategoriesRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllDishCategoriesRequest.class);
        }
        AllDishCategoriesRequest allDishCategoriesRequest = (AllDishCategoriesRequest) mobileRequest;
        return handleRequest(allDishCategoriesRequest);
    }
}
