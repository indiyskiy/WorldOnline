package controller.phone.parser;

import com.google.gson.JsonObject;
import model.constants.Status;

public class MobileErrorParser {
    public String parse(Exception error) {
        String message = error.getMessage();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", message);
        jsonObject.addProperty("status", Status.Error.toString());
        return jsonObject.toString();
    }

}
