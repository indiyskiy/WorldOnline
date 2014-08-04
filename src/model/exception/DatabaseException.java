package model.exception;

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
