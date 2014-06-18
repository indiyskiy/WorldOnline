package model.database.worldonlinedb;

import model.constants.databaseenumeration.CardImageType;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 31.10.13
 * Time: 1:43
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "CardImage", schema = "", catalog = "worldonline")
@Entity
public class CardImageEntity {
    @javax.persistence.Column(name = "CardImageID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardImageID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ImageID")
    private ImageEntity image;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @javax.persistence.Column(name = "CardImageType")
    @Basic
    private Integer cardImageType;

    @javax.persistence.Column(name = "CardImageName")
    @Basic
    private String cardImageName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ImageDescriptionTextGroupID")
    private TextGroupEntity imageDescriptionTextGroup;

    public Long getCardImageID() {
        return cardImageID;
    }

    public void setCardImageID(Long cardImageID) {
        this.cardImageID = cardImageID;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public Integer getCardImageType() {
        return cardImageType;
    }

    public void setCardImageType(Integer cardImageType) {
        this.cardImageType = cardImageType;
    }

    public String getCardImageName() {
        return cardImageName;
    }

    public void setCardImageName(String cardImageName) {
        this.cardImageName = cardImageName;
    }

    public TextGroupEntity getImageDescriptionTextGroup() {
        return imageDescriptionTextGroup;
    }

    public void setImageDescriptionTextGroup(TextGroupEntity imageDescriptionTextGroup) {
        this.imageDescriptionTextGroup = imageDescriptionTextGroup;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardImageEntity that = (CardImageEntity) o;

        if (cardImageID != null ? !cardImageID.equals(that.cardImageID) : that.cardImageID != null)
            return false;
        if (card != null ? !card.equals(that.card) : that.card != null)
            return false;
        if (image != null ? !image.equals(that.image) : that.image != null)
            return false;
        if (cardImageType != null ? !cardImageType.equals(that.cardImageType) : that.cardImageType != null)
            return false;
        if (cardImageName != null ? !cardImageName.equals(that.cardImageName) : that.cardImageName != null)
            return false;
        if (imageDescriptionTextGroup != null ? !imageDescriptionTextGroup.equals(that.imageDescriptionTextGroup) : that.imageDescriptionTextGroup != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardImageID != null ? cardImageID.hashCode() : 0;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (cardImageType != null ? cardImageType.hashCode() : 0);
        result = 31 * result + (cardImageName != null ? cardImageName.hashCode() : 0);
        result = 31 * result + (imageDescriptionTextGroup != null ? imageDescriptionTextGroup.hashCode() : 0);
        return result;
    }

    public CardImageEntity() {
    }

    public CardImageEntity(CardEntity card, ImageEntity image, CardImageType cardCardImageType) {
        this.card = card;
        this.image = image;
        this.cardImageType = cardCardImageType.getValue();
        this.cardImageName = card.getCardName() + "-" + cardCardImageType;
    }
}
