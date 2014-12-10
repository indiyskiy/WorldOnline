package model.additionalentity.admin;

import model.constants.UserCardState;

public class SimpleUserCard {
    private Long cardID;
    private UserCardState userCardState;
    private String name;

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public UserCardState getUserCardState() {
        return userCardState;
    }

    public void setUserCardState(UserCardState userCardState) {
        this.userCardState = userCardState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleUserCard(Long cardID, UserCardState userCardState, String name) {
        this.cardID = cardID;
        this.userCardState = userCardState;
        this.name = name;
    }
}
