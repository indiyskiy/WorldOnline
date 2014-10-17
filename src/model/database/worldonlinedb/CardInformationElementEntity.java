package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "CardInformationElement", schema = "", catalog = "worldonline")
@Entity
public class CardInformationElementEntity {

    @javax.persistence.Column(name = "CardInformationElementID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardInformationElementID;

    @javax.persistence.Column(name = "Position")
    @Basic
    private Integer position;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TextGroupID")
    private TextGroupEntity textGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ImageID")
    private ImageEntity image;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    public Long getCardInformationElementID() {
        return cardInformationElementID;
    }

    public void setCardInformationElementID(Long cardInformationElementID) {
        this.cardInformationElementID = cardInformationElementID;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public TextGroupEntity getTextGroup() {
        return textGroup;
    }

    public void setTextGroup(TextGroupEntity textGroup) {
        this.textGroup = textGroup;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }
}
