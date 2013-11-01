package model.database;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.HelpRelationEntityPK.class)
@javax.persistence.Table(name = "help_relation", schema = "", catalog = "mysql")
@Entity
public class HelpRelationEntity {
    private int helpTopicId;

    @javax.persistence.Column(name = "help_topic_id")
    @Id
    public int getHelpTopicId() {
        return helpTopicId;
    }

    public void setHelpTopicId(int helpTopicId) {
        this.helpTopicId = helpTopicId;
    }

    private int helpKeywordId;

    @javax.persistence.Column(name = "help_keyword_id")
    @Id
    public int getHelpKeywordId() {
        return helpKeywordId;
    }

    public void setHelpKeywordId(int helpKeywordId) {
        this.helpKeywordId = helpKeywordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HelpRelationEntity that = (HelpRelationEntity) o;

        if (helpKeywordId != that.helpKeywordId) return false;
        if (helpTopicId != that.helpTopicId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = helpTopicId;
        result = 31 * result + helpKeywordId;
        return result;
    }
}
