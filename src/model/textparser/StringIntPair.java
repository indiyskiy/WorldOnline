package model.textparser;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
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
