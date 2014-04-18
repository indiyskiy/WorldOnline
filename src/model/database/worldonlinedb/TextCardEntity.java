package model.database.worldonlinedb;

import model.constants.databaseenumeration.TextType;

import javax.persistence.*;

@javax.persistence.Table(name = "TextCard", schema = "", catalog = "worldonline")
@Entity
public class TextCardEntity {
    @javax.persistence.Column(name = "TextCardID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long textCardID;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TextGroupID")
    private TextGroupEntity textGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @javax.persistence.Column(name = "CardTextType")
    @Basic
    private Integer cardTextType;

    public Long getTextCardID() {
        return textCardID;
    }

    public void setTextCardID(Long textCardID) {
        this.textCardID = textCardID;
    }

    public TextGroupEntity getTextGroup() {
        return textGroup;
    }

    public void setTextGroup(TextGroupEntity textGroup) {
        this.textGroup = textGroup;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public Integer getCardTextType() {
        return cardTextType;
    }

    public void setCardTextType(Integer cardTextType) {
        this.cardTextType = cardTextType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextCardEntity that = (TextCardEntity) o;

        if (textCardID != null ? !textCardID.equals(that.textCardID) : that.textCardID != null)
            return false;
        if (textGroup != null ? !textGroup.equals(that.textGroup) : that.textGroup != null)
            return false;
        if (card != null ? !card.equals(that.card) : that.card != null)
            return false;
        if (cardTextType != null ? !cardTextType.equals(that.cardTextType) : that.cardTextType != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = textCardID != null ? textCardID.hashCode() : 0;
        result = 31 * result + (textGroup != null ? textGroup.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (cardTextType != null ? cardTextType.hashCode() : 0);
        return result;
    }

    public TextCardEntity() {
    }

    public TextCardEntity(TextGroupEntity textGroup, CardEntity card, TextType cardTextType) {
        setTextGroup(textGroup);
        setCard(card);
        setCardTextType(cardTextType.getValue());
    }
}
