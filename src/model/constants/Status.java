package model.constants;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public enum Status {
    Error(0),
    OK(1);
    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
