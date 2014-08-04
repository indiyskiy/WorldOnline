package model.exception;

public class IllegalTypeException extends Exception {
    private Class from;
    private Class to;

    public IllegalTypeException(Class from, Class to) {
        super();
        this.from = from;
        this.to = to;
    }

    public String getMessage() {
        String message = "Can't parse from " + from + " to " + to + " class. Please, check the class declaration.";
        return message;
    }
}
