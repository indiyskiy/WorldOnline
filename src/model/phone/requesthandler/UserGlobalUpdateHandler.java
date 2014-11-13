package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class UserGlobalUpdateHandler implements MobileHandler {
    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        return null;
    }
}
