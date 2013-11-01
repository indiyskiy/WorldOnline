package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 30.10.13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Text", schema = "", catalog = "worldonline")
@Entity
public class TextEntity {
    @javax.persistence.Column(name = "TextID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long textID;

    public Long getTextID() {
        return textID;
    }

    public void setTextID(Long textID) {
        this.textID = textID;
    }

    @javax.persistence.Column(name = "Text")
    @Basic
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @javax.persistence.Column(name = "LanguageID")
    @Basic
    private Integer languageID;

    public Integer getLanguageID() {
        return languageID;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TextGroupID")
    private TextGroupEntity textGroup;

    public TextGroupEntity getTextGroup() {
        return textGroup;
    }

    public void setTextGroup(TextGroupEntity textGroup) {
        this.textGroup = textGroup;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextEntity that = (TextEntity) o;

        if (textID != null ? !textID.equals(that.textID) : that.textID != null)
            return false;
        if (text != null ? !text.equals(that.text) : that.text != null)
            return false;
        if (languageID != null ? !languageID.equals(that.languageID) : that.languageID != null)
            return false;
        if (textGroup != null ? !textGroup.equals(that.textGroup) : that.textGroup != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = textID != null ? textID.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (languageID != null ? languageID.hashCode() : 0);
        result = 31 * result + (textGroup != null ? textGroup.hashCode() : 0);
        return result;
    }
}

