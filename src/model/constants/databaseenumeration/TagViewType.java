package model.constants.databaseenumeration;


public enum TagViewType {
    Icons(1),
    Text(2),
    MenuIcon(3);
    private final int value;

    TagViewType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
