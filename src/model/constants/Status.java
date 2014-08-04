package model.constants;

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
