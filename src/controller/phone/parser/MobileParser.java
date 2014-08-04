package controller.phone.parser;

import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

public abstract class MobileParser {
    public abstract MobileRequest parse(HttpServletRequest request) throws ParseRequestException;

    public static final String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        return mobileResponseEntity.getJson().toString();
    }
}
