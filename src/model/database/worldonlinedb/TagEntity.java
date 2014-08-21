package model.database.worldonlinedb;

import model.constants.ApplicationBlock;
import model.constants.databaseenumeration.TagViewType;

import javax.persistence.*;

@javax.persistence.Table(name = "Tag", schema = "", catalog = "worldonline")
@Entity
public class TagEntity {
    @javax.persistence.Column(name = "TagID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagID;

    @javax.persistence.Column(name = "TagName")
    @Basic
    private String tagName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TagTextGroupID")
    private TextGroupEntity tagTextGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TagGroupID")
    private TagGroupEntity tagGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "IconID")
    private ImageEntity icon;


    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public TextGroupEntity getTagTextGroup() {
        return tagTextGroup;
    }

    public void setTagTextGroup(TextGroupEntity tagTextGroup) {
        this.tagTextGroup = tagTextGroup;
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
        return true;
    }

    @Override
    public int hashCode() {
        int result = tagID != null ? tagID.hashCode() : 0;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + (tagTextGroup != null ? tagTextGroup.hashCode() : 0);
        return result;
    }

    public TagEntity() {

    }

    public TagEntity(TextGroupEntity tagTextGroup, String tagName, TagGroupEntity tagGroup, ImageEntity imageEntity) {
        setTagTextGroup(tagTextGroup);
        setTagName(tagName);
        setTagGroup(tagGroup);
        setIcon(imageEntity);

    }

    public TagGroupEntity getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(TagGroupEntity tagGroupEntity) {
        this.tagGroup = tagGroupEntity;
    }

    public ImageEntity getIcon() {
        return icon;
    }

    public void setIcon(ImageEntity image) {
        this.icon = image;
    }


}
