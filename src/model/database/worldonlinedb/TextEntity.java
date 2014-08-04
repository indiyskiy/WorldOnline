package model.database.worldonlinedb;

import model.constants.databaseenumeration.LanguageType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@javax.persistence.Table(name = "Text", schema = "", catalog = "worldonline")
@Entity
public class TextEntity {
    @javax.persistence.Column(name = "TextID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long textID;

    @javax.persistence.Column(name = "Text")
    @Type(type = "text")
    @Basic
    private String text;

    @javax.persistence.Column(name = "LanguageID")
    @Basic
    private Integer languageID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TextGroupID")
    private TextGroupEntity textGroup;

    public Long getTextID() {
        return textID;
    }

    public void setTextID(Long textID) {
        this.textID = textID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLanguageID() {
        return languageID;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

    public TextGroupEntity getTextGroup() {
        return textGroup;
    }

    public void setTextGroup(TextGroupEntity textGroup) {
        this.textGroup = textGroup;
    }


    public TextEntity() {

    }

    public TextEntity(LanguageType languageType, String text, TextGroupEntity textGroup) {
        setLanguageID(languageType.getValue());
        setText(text);
        setTextGroup(textGroup);
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

