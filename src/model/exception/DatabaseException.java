package model.exception;

/**
 * Created by Илья on 25.04.14.
 */
public class DatabaseException extends Exception {
    private String message;

    public DatabaseException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
