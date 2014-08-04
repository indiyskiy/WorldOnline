package model.textparser;

public class RequestParser {
    public static String shieldText(String requestText) {
        requestText = requestText.replaceAll("\"", "'");
        requestText = requestText.replaceAll("  ", " ");
        return requestText;
    }
}
