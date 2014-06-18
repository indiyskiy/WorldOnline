package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.ServletException;

/**
 * Created by Илья on 10.04.14.
 */
public interface MobileHandler {
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException;
}
