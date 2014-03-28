package model.database.worldonlinedb;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Илья on 27.03.14.
 */
@javax.persistence.Table(name = "CardChange", schema = "", catalog = "worldonline")
@Entity
public class CardChangeEntity {
    @javax.persistence.Column(name = "CardChangeID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardChangeID;


    @javax.persistence.Column(name = "DataType")
    @Basic
    private Integer dataType;

    @javax.persistence.Column(name = "UpdateType")
    @Basic
    private Integer updateType;

    @javax.persistence.Column(name = "UpdateStatus")
    @Basic
    private Integer updateStatus;

    @javax.persistence.Column(name = "ChangingTimestamp")
    @Basic
    private Timestamp changingTimestamp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ChangingAdminUser")
    private AdminUserEntity changingAdminUser;


    public Long getCardChangeID() {
        return cardChangeID;
    }

    public void setCardChangeID(Long cardChangeID) {
        this.cardChangeID = cardChangeID;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getUpdateType() {
        return updateType;
    }

    public void setUpdateType(Integer updateType) {
        this.updateType = updateType;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Timestamp getChangingTimestamp() {
        return changingTimestamp;
    }

    public void setChangingTimestamp(Timestamp changingTimestamp) {
        this.changingTimestamp = changingTimestamp;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }


}
