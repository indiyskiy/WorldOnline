package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Илья on 18.03.14.
 */
@javax.persistence.Table(name = "UserCard", schema = "", catalog = "worldonline")
@Entity
public class UserCardEntity {
    @javax.persistence.Column(name = "UserCardID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCardID;

    @javax.persistence.Column(name = "CardVersion")
    @Basic
    private Long cardVersion;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @javax.persistence.Column(name = "LastUpdateTimestamp")
    @Basic
    private Timestamp lastUpdateTimestamp;

    @javax.persistence.Column(name = "CardState")
    @Basic
    private Integer cardState;

    public UserCardEntity() {

    }

    public UserCardEntity(UserEntity userEntity, CardEntity cardEntity) {
        this.card = cardEntity;
        this.userContent = userEntity.getUserContent();
        this.cardVersion = cardEntity.getCardVersion();
        this.cardState = cardEntity.getCardState();
        updateLastUpdateTimestamp();
    }

    public UserCardEntity(UserContentEntity userContentEntity, CardEntity cardEntity) {
        this.card = cardEntity;
        this.userContent = userContentEntity;
        this.cardVersion = cardEntity.getCardVersion();
        this.cardState = cardEntity.getCardState();
        updateLastUpdateTimestamp();
    }

    public Long getUserCardID() {
        return userCardID;
    }

    public void setUserCardID(Long userCardID) {
        this.userCardID = userCardID;
    }

    public Long getVersion() {
        return cardVersion;
    }

    public void setVersion(Long version) {
        this.cardVersion = version;
    }

    public UserContentEntity getUserContent() {
        return userContent;
    }

    public void setUserContent(UserContentEntity userContent) {
        this.userContent = userContent;
    }

    public Long getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(Long cardVersion) {
        this.cardVersion = cardVersion;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer status) {
        this.cardState = status;
    }

    public Timestamp getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public void updateLastUpdateTimestamp() {
        this.lastUpdateTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
