package model.additionalentity.admin;

import java.util.ArrayList;

public class CardBlock {
    private String name;
    private int position;
    private int id;
    private ArrayList<CardParameter> cardParameters = new ArrayList<>();
    private ArrayList<CardText> cardTexts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<CardParameter> getCardParameters() {
        return cardParameters;
    }

    public void setCardParameters(ArrayList<CardParameter> cardParameters) {
        this.cardParameters = cardParameters;
    }

    public ArrayList<CardText> getCardTexts() {
        return cardTexts;
    }

    public void setCardTexts(ArrayList<CardText> cardTexts) {
        this.cardTexts = cardTexts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsEmpty() {
        return cardParameters.isEmpty() && cardTexts.isEmpty();
    }
}
