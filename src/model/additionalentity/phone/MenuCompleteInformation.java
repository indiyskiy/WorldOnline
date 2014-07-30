package model.additionalentity.phone;

public class MenuCompleteInformation {
    private Long menuID;
    private Long iconImageID;
    private Long parentMenuID;
    private String text;
    private Integer number;
    private Integer cardCounter;
    //значение было пребавлено родителю
    private boolean counted = false;
    //узел готов быть пребавленным родителю(все дети были прибавлены)
    private boolean readyForCounting = true;

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public Long getMenuID() {
        return menuID;
    }

    public void setIconImageID(Long iconImageID) {
        this.iconImageID = iconImageID;
    }

    public Long getIconImageID() {
        return iconImageID;
    }


    public void setParentMenuID(Long parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

    public Long getParentMenuID() {
        return parentMenuID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setCardCounter(Integer cardCounter) {
        this.cardCounter = cardCounter;
    }

    public Integer getCardCounter() {
        return cardCounter;
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    public boolean isReadyForCounting() {
        return readyForCounting;
    }

    public void setReadyForCounting(boolean readyForCounting) {
        this.readyForCounting = readyForCounting;
    }
}
