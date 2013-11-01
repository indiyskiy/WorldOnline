package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 30.10.13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Tag", schema = "", catalog = "worldonline")
@Entity
public class TagEntity {
    @javax.persistence.Column(name = "TagID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagID;

    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    @javax.persistence.Column(name = "TagName")
    @Basic
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TagTextGroupID")
    private TextGroupEntity tagTextGroup;

    public TextGroupEntity getTagTextGroup() {
        return tagTextGroup;
    }

    public void setTagTextGroup(TextGroupEntity tagTextGroup) {
        this.tagTextGroup = tagTextGroup;
    }

    @javax.persistence.Column(name = "TagType")
    @Basic
    private Integer tagType;

    public Integer getTagType() {
        return tagType;
    }

    public void setTagType(Integer tagType) {
        this.tagType = tagType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagEntity that = (TagEntity) o;

        if (tagID != null ? !tagID.equals(that.tagID) : that.tagID != null)
            return false;
        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null)
            return false;
        if (tagTextGroup != null ? !tagTextGroup.equals(that.tagTextGroup) : that.tagTextGroup != null)
            return false;
        if (tagType != null ? !tagType.equals(that.tagType) : that.tagType != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = tagID != null ? tagID.hashCode() : 0;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + (tagTextGroup != null ? tagTextGroup.hashCode() : 0);
        result = 31 * result + (tagType != null ? tagType.hashCode() : 0);
        return result;
    }
}
