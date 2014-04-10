package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;

/**
 * Created by Илья on 10.04.14.
 */
public interface MobileHandler {
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException;
}
