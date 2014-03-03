package controller.phone.parser;

import com.google.gson.JsonObject;
import model.constants.Status;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class MobileErrorParser {
    public static String parse(Exception error) {
        String message=error.getMessage();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("error",message);
        jsonObject.addProperty("status", Status.Error.toString());
        return  jsonObject.toString();
    }


}
