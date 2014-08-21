package model.constants.databaseenumeration;

public enum NoBarrierTag implements TagEnum {
    a(13, "Доступность для инвалидов по зрению", "Accessibility for the Visually Impaired", "withoutBarrier14.png"),
    b(12, "Доступность для инвалидов по слуху", "Accessibility for people with hearing disabilities", "withoutBarrier13.png"),
    c(11, "Функциональная зона(места для зрителей, стойка регистрации и т.д.)",
            "Functional area(seats for spectators, reception, etc.)",
            "withoutBarrier12.png"),
    d(10, "Информационные указатели доступных путей передвижения",
            "Information signs for available paths of movement",
            "withoutBarrier11.png"),
    e(9, "лифты, подъемники, лестницы", "lifts, elevators, stairs", "withoutBarrier10.png"),
    f(8, "Маршрут от парковки до объекта", "The route from the parking lot to the facility", "withoutBarrier9.png"),
    g(7, "Пандусы/пониженные бордюры", "lowered curbs", "withoutBarrier8.png"),
    h(6, "Парковка", "parking", "withoutBarrier7.png"),
    i(5, "Персонал подготовлен к работе с людьми с инвалидностью",
            "The staff is prepared to work with people with disabilities",
            "withoutBarrier6.png"),
    k(4, "Пути эвакуации", "evacuation routes", "withoutBarrier5.png"),
    l(3, "Зона питания", "eating area", "withoutBarrier4.png"),
    m(2, "Туалетная комната", "toilet room", "withoutBarrier3.png"),
    n(1, "Пути передвижения в здании", "Paths of movement in the building", "withoutBarrier2.png"),
    o(0, "Вход в здание", "Entrance to the building", "withoutBarrier1.png");

    private final int value;
    private final String nameRu;
    private final String nameEn;
    private final String imageName;

    private NoBarrierTag(
            int value, String
            nameRu,
            String nameEN, String
            imageName) {
        this.value = value;
        this.nameRu = nameRu;
        this.nameEn = nameEN;
        this.imageName = imageName;
    }

    public int getValue() {
        return value;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getImageName() {
        return imageName;
    }

    public static NoBarrierTag parse(String tag) {
        if (tag != null && !tag.isEmpty()) {
            return NoBarrierTag.values()[Integer.parseInt(tag)];
        }
        return null;
    }

    @Override
    public TagEnum setByValue(String value) {
        return MiddlePrice.values()[Integer.parseInt(value)];
    }

    @Override
    public TagType getTagType() {
        return TagType.NoBarriers;
    }

    @Override
    public TagEnum[] allValues() {
        return values();
    }
}

