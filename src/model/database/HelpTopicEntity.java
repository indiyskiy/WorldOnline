package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "help_topic", schema = "", catalog = "mysql")
@Entity
public class HelpTopicEntity {
    private int helpTopicId;

    @javax.persistence.Column(name = "help_topic_id")
    @Id
    public int getHelpTopicId() {
        return helpTopicId;
    }

    public void setHelpTopicId(int helpTopicId) {
        this.helpTopicId = helpTopicId;
    }

    private String name;

    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private short helpCategoryId;

    @javax.persistence.Column(name = "help_category_id")
    @Basic
    public short getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(short helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
    }

    private String description;

    @javax.persistence.Column(name = "description")
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String example;

    @javax.persistence.Column(name = "example")
    @Basic
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    private String url;

    @javax.persistence.Column(name = "url")
    @Basic
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HelpTopicEntity that = (HelpTopicEntity) o;

        if (helpCategoryId != that.helpCategoryId) return false;
        if (helpTopicId != that.helpTopicId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (example != null ? !example.equals(that.example) : that.example != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = helpTopicId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) helpCategoryId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (example != null ? example.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
