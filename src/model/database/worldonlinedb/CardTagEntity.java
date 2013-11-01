package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 31.10.13
 * Time: 0:25
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "CardTag", schema = "", catalog = "worldonline")
@Entity
public class CardTagEntity {
    @javax.persistence.Column(name = "CardTagID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardTagID;

    public Long getCardTagID() {
        return cardTagID;
    }

    public void setCardTagID(Long cardTagID) {
        this.cardTagID = cardTagID;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TagID")
    private TagEntity tag;

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardTagEntity that = (CardTagEntity) o;

        if (cardTagID != null ? !cardTagID.equals(that.cardTagID) : that.cardTagID != null)
            return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardTagID != null ? cardTagID.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
