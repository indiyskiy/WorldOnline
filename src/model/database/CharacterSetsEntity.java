package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "CHARACTER_SETS", schema = "", catalog = "information_schema")
@Entity
public class CharacterSetsEntity {
    private String characterSetName;

    @javax.persistence.Column(name = "CHARACTER_SET_NAME")
    @Basic
    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    private String defaultCollateName;

    @javax.persistence.Column(name = "DEFAULT_COLLATE_NAME")
    @Basic
    public String getDefaultCollateName() {
        return defaultCollateName;
    }

    public void setDefaultCollateName(String defaultCollateName) {
        this.defaultCollateName = defaultCollateName;
    }

    private String description;

    @javax.persistence.Column(name = "DESCRIPTION")
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private long maxlen;

    @javax.persistence.Column(name = "MAXLEN")
    @Basic
    public long getMaxlen() {
        return maxlen;
    }

    public void setMaxlen(long maxlen) {
        this.maxlen = maxlen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterSetsEntity that = (CharacterSetsEntity) o;

        if (maxlen != that.maxlen) return false;
        if (characterSetName != null ? !characterSetName.equals(that.characterSetName) : that.characterSetName != null)
            return false;
        if (defaultCollateName != null ? !defaultCollateName.equals(that.defaultCollateName) : that.defaultCollateName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = characterSetName != null ? characterSetName.hashCode() : 0;
        result = 31 * result + (defaultCollateName != null ? defaultCollateName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (maxlen ^ (maxlen >>> 32));
        return result;
    }
}
