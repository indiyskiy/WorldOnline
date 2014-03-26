package model.textparser;

/**
 * Created by Илья on 19.03.14.
 */
public class RequestParser {
    public static String shieldText(String requestText) {
        requestText = requestText.replaceAll("\"", "'");
        requestText = requestText.replaceAll("  ", " ");
        return requestText;
    }
}
