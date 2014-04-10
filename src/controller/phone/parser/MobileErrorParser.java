package controller.phone.parser;

import com.google.gson.JsonObject;
import controller.phone.entity.AllMenusRequest;
import model.constants.Status;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class MobileErrorParser {
    public String parse(Exception error) {
        String message = error.getMessage();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", message);
        jsonObject.addProperty("status", Status.Error.toString());
        return jsonObject.toString();
    }

}
