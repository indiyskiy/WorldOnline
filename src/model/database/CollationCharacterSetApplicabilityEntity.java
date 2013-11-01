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
@javax.persistence.Table(name = "COLLATION_CHARACTER_SET_APPLICABILITY", schema = "", catalog = "information_schema")
@Entity
public class CollationCharacterSetApplicabilityEntity {
    private String collationName;

    @javax.persistence.Column(name = "COLLATION_NAME")
    @Basic
    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    private String characterSetName;

    @javax.persistence.Column(name = "CHARACTER_SET_NAME")
    @Basic
    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollationCharacterSetApplicabilityEntity that = (CollationCharacterSetApplicabilityEntity) o;

        if (characterSetName != null ? !characterSetName.equals(that.characterSetName) : that.characterSetName != null)
            return false;
        if (collationName != null ? !collationName.equals(that.collationName) : that.collationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collationName != null ? collationName.hashCode() : 0;
        result = 31 * result + (characterSetName != null ? characterSetName.hashCode() : 0);
        return result;
    }
}
