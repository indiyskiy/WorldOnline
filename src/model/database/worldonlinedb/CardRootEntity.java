package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 31.10.13
 * Time: 3:51
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "CardRoot", schema = "", catalog = "worldonline")
@Entity
public class CardRootEntity {
    @javax.persistence.Column(name = "CardRootID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardRootID;

    public Long getCardRootID() {
        return cardRootID;
    }

    public void setCardRootID(Long cardRootID) {
        this.cardRootID = cardRootID;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    @javax.persistence.Column(name = "CardRootName")
    @Basic
    private String cardRootName;

    public String getCardRootName() {
        return cardRootName;
    }

    public void setCardRootName(String cardRootName) {
        this.cardRootName = cardRootName;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RootDescriptionTextGroupID")
    private TextGroupEntity rootDescriptionTextGroup;

    public TextGroupEntity getRootDescriptionTextGroup() {
        return rootDescriptionTextGroup;
    }

    public void setRootDescriptionTextGroup(TextGroupEntity rootDescriptionTextGroup) {
        this.rootDescriptionTextGroup = rootDescriptionTextGroup;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardRootEntity that = (CardRootEntity) o;

        if (cardRootID != null ? !cardRootID.equals(that.cardRootID) : that.cardRootID != null)
            return false;
        if (card != null ? !card.equals(that.card) : that.card != null)
            return false;
        if (cardRootName != null ? !cardRootName.equals(that.cardRootName) : that.cardRootName != null)
            return false;
        if (rootDescriptionTextGroup != null ? !rootDescriptionTextGroup.equals(that.rootDescriptionTextGroup) : that.rootDescriptionTextGroup != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardRootID != null ? cardRootID.hashCode() : 0;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (cardRootName != null ? cardRootName.hashCode() : 0);
        result = 31 * result + (rootDescriptionTextGroup != null ? rootDescriptionTextGroup.hashCode() : 0);
        return result;
    }
}
