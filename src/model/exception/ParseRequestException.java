package model.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 15:28
 * To change this template use File | Settings | File Templates.
 */
public class ParseRequestException extends Exception {
    private String message;

    public ParseRequestException(String message) {
        super();
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
