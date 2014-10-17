package helper;

import model.constants.databaseenumeration.DataType;

public class InvalidDataTypeException extends Exception {
    private String value;
    private DataType dataType;

    public InvalidDataTypeException(String value, DataType dataType) {
        this.value = value;
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public DataType getDataType() {
        return dataType;
    }

    public String toString() {
        return "illegal value \"" + value + "\" with type " + dataType;
    }
}
