package model.exception;

public class ParseRequestException extends Exception {
    private String message;

    public ParseRequestException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
