package controller.phone.parser;

import controller.phone.entity.MobileRequest;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class MobileParser {
    public abstract MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException;

    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        return mobileResponseEntity.getJson().toString();
    }
}
