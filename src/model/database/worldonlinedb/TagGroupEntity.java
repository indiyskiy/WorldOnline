package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "TagGroup", schema = "", catalog = "worldonline")
@Entity
public class TagGroupEntity {
    @javax.persistence.Column(name = "TagGroupID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TagGroupID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TagGroupTextGroupID")
    private TextGroupEntity tagGroupTextGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @javax.persistence.Column(name = "Position")
    @Basic
    private Integer position;

    public Long getTagGroupID() {
        return TagGroupID;
    }

    public void setTagGroupID(Long tagGroupID) {
        TagGroupID = tagGroupID;
    }

    public TextGroupEntity getTagGroupTextGroup() {
        return tagGroupTextGroup;
    }

    public void setTagGroupTextGroup(TextGroupEntity tagGroupTextGroup) {
        this.tagGroupTextGroup = tagGroupTextGroup;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public TagGroupEntity(TextGroupEntity tagGroupTextGroup, CardEntity card, Integer position) {
        this.tagGroupTextGroup = tagGroupTextGroup;
        this.card = card;
        this.position = position;
    }

    public TagGroupEntity() {
    }
}
