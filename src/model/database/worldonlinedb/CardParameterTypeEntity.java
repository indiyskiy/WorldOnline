package model.database.worldonlinedb;

import model.constants.ApplicationBlock;
import model.constants.databaseenumeration.DataType;

import javax.persistence.*;

@javax.persistence.Table(name = "CardParameterType", schema = "", catalog = "worldonline")
@Entity
public class CardParameterTypeEntity {
    @javax.persistence.Column(name = "CardParameterTypeID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardParameterTypeID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardParameterTypeName")
    private TextGroupEntity cardParameterTypeName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ImageID")
    private ImageEntity image;

    @javax.persistence.Column(name = "DataType")
    @Basic
    private Integer dataType;

    @javax.persistence.Column(name = "Block")
    @Basic
    private Integer block;

    @javax.persistence.Column(name = "Position")
    @Basic
    private Integer position;

    @javax.persistence.Column(name = "Translatable")
    @Basic
    private boolean translatable;

    @javax.persistence.Column(name = "Multiply")
    @Basic
    private boolean multiply;

    public boolean isTranslatable() {
        return translatable;
    }

    public void setTranslatable(boolean translatable) {
        this.translatable = translatable;
    }

    public boolean isMultiply() {
        return multiply;
    }

    public void setMultiply(boolean multiply) {
        this.multiply = multiply;
    }

    public Long getCardParameterTypeID() {
        return cardParameterTypeID;
    }

    public void setCardParameterTypeID(Long cardParameterTypeID) {
        this.cardParameterTypeID = cardParameterTypeID;
    }

    public TextGroupEntity getCardParameterTypeName() {
        return cardParameterTypeName;
    }

    public void setCardParameterTypeName(TextGroupEntity cardParameterTypeName) {
        this.cardParameterTypeName = cardParameterTypeName;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer parameterType) {
        this.dataType = parameterType;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public CardParameterTypeEntity() {
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public CardParameterTypeEntity(TextGroupEntity cardParameterTypeName, ImageEntity image, DataType dataType, ApplicationBlock block, Integer position, boolean translatable, boolean multiply) {
        this.cardParameterTypeName = cardParameterTypeName;
        this.image = image;
        this.dataType = dataType.getValue();
        this.block = block.getValue();
        this.position = position;
        this.translatable = translatable;
        this.multiply = multiply;
    }
}
