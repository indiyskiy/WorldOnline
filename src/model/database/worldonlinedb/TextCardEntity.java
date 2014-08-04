package model.database.worldonlinedb;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardParameterTypeID")
    private CardParameterTypeEntity cardParameterType;

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


    public CardParameterTypeEntity getCardParameterType() {
        return cardParameterType;
    }

    public void setCardParameterType(CardParameterTypeEntity cardParameterType) {
        this.cardParameterType = cardParameterType;
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
        if (cardParameterType != null ? !cardParameterType.equals(that.cardParameterType) : that.cardParameterType != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = textCardID != null ? textCardID.hashCode() : 0;
        result = 31 * result + (textGroup != null ? textGroup.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (cardParameterType != null ? cardParameterType.hashCode() : 0);
        return result;
    }

    public TextCardEntity() {
    }

    public TextCardEntity(TextGroupEntity textGroup, CardEntity card, CardParameterTypeEntity cardParameterType) {
        setTextGroup(textGroup);
        setCard(card);
        setCardParameterType(cardParameterType);
    }
}
