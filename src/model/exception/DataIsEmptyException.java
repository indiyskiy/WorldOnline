package model.exception;

public class DataIsEmptyException extends Exception {
    private String imageName;

    public DataIsEmptyException(String imageName) {
        this.imageName = imageName;
    }

    public DataIsEmptyException() {

    }

    public String getImageName() {
        return imageName;
    }

}
