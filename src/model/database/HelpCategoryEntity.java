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
@javax.persistence.Table(name = "help_category", schema = "", catalog = "mysql")
@Entity
public class HelpCategoryEntity {
    private short helpCategoryId;

    @javax.persistence.Column(name = "help_category_id")
    @Id
    public short getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(short helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
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

    private short parentCategoryId;

    @javax.persistence.Column(name = "parent_category_id")
    @Basic
    public short getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(short parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
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

        HelpCategoryEntity that = (HelpCategoryEntity) o;

        if (helpCategoryId != that.helpCategoryId) return false;
        if (parentCategoryId != that.parentCategoryId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) helpCategoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) parentCategoryId;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
