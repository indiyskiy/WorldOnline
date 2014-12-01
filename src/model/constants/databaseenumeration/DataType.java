package model.constants.databaseenumeration;

public enum DataType {
    UnknownType(0, "Неизвестно"),
    IntegerType(1, "Натуральное число"),
    DoubleType(2, "Дробное число"),
    StringType(3, "Текст"),
    LinkType(4, "Ссылка"),
    EmailType(5, "Мэил"),
    PhoneNumberType(6, "Телефонный номер"),
    TimestampType(7, "Дата"),
    Percent(8, "Проценты"),
    Cost(9, "Цена"),
    Boolean(10, "Истина/ложь"),
    Header(11, "Заголовок");


    private final int value;
    private final String ruName;

    private DataType(int value, String ruName) {
        this.value = value;
        this.ruName = ruName;
    }

    public int getValue() {
        return value;
    }

    public static DataType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        DataType[] dataTypes = DataType.values();
        if (value <= 0 || value >= dataTypes.length) {
            return UnknownType;
        } else {
            return dataTypes[value];
        }
    }

    public String getRuName() {
        return ruName;
    }
}
