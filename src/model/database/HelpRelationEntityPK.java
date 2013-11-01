package model.database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
public class HelpRelationEntityPK implements Serializable {
    private int helpTopicId;

    @Id
    @Column(name = "help_topic_id")
    public int getHelpTopicId() {
        return helpTopicId;
    }

    public void setHelpTopicId(int helpTopicId) {
        this.helpTopicId = helpTopicId;
    }

    private int helpKeywordId;

    @Id
    @Column(name = "help_keyword_id")
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

        HelpRelationEntityPK that = (HelpRelationEntityPK) o;

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
