package model.textparser;

public class StringIntPair {
    private String string;
    private int anInt;

    public StringIntPair(String string, int anInt) {
        this.string = string;
        this.anInt = anInt;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
}
