package controller.phone.parser;

import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 10.04.14.
 */
public interface MobileParser {
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException;

    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException;
}
