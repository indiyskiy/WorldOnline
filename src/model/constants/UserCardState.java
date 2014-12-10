package model.constants;


public enum UserCardState {
    Actual(1, "Актуальные"),
    Update(2, "На обновление"),
    Delete(3, "На удаление"),
    Download(4, "На скачку"),
    Unknown(5, "Неведомая фигня");

    private final int value;
    private final String groupName;

    UserCardState(int value, String groupName) {
        this.value = value;
        this.groupName = groupName;
    }

    public int getValue() {
        return value;
    }

    public String getGroupName() {
        return groupName;
    }

    public static UserCardState parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        UserCardState[] languageTypes = UserCardState.values();
        return languageTypes[value - 1];
    }
}
