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
@javax.persistence.IdClass(model.database.DbEntityPK.class)
@javax.persistence.Table(name = "db", schema = "", catalog = "mysql")
@Entity
public class DbEntity {
    private String host;

    @javax.persistence.Column(name = "Host")
    @Id
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String db;

    @javax.persistence.Column(name = "Db")
    @Id
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String user;

    @javax.persistence.Column(name = "User")
    @Id
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String selectPriv;

    @javax.persistence.Column(name = "Select_priv")
    @Basic
    public String getSelectPriv() {
        return selectPriv;
    }

    public void setSelectPriv(String selectPriv) {
        this.selectPriv = selectPriv;
    }

    private String insertPriv;

    @javax.persistence.Column(name = "Insert_priv")
    @Basic
    public String getInsertPriv() {
        return insertPriv;
    }

    public void setInsertPriv(String insertPriv) {
        this.insertPriv = insertPriv;
    }

    private String updatePriv;

    @javax.persistence.Column(name = "Update_priv")
    @Basic
    public String getUpdatePriv() {
        return updatePriv;
    }

    public void setUpdatePriv(String updatePriv) {
        this.updatePriv = updatePriv;
    }

    private String deletePriv;

    @javax.persistence.Column(name = "Delete_priv")
    @Basic
    public String getDeletePriv() {
        return deletePriv;
    }

    public void setDeletePriv(String deletePriv) {
        this.deletePriv = deletePriv;
    }

    private String createPriv;

    @javax.persistence.Column(name = "Create_priv")
    @Basic
    public String getCreatePriv() {
        return createPriv;
    }

    public void setCreatePriv(String createPriv) {
        this.createPriv = createPriv;
    }

    private String dropPriv;

    @javax.persistence.Column(name = "Drop_priv")
    @Basic
    public String getDropPriv() {
        return dropPriv;
    }

    public void setDropPriv(String dropPriv) {
        this.dropPriv = dropPriv;
    }

    private String grantPriv;

    @javax.persistence.Column(name = "Grant_priv")
    @Basic
    public String getGrantPriv() {
        return grantPriv;
    }

    public void setGrantPriv(String grantPriv) {
        this.grantPriv = grantPriv;
    }

    private String referencesPriv;

    @javax.persistence.Column(name = "References_priv")
    @Basic
    public String getReferencesPriv() {
        return referencesPriv;
    }

    public void setReferencesPriv(String referencesPriv) {
        this.referencesPriv = referencesPriv;
    }

    private String indexPriv;

    @javax.persistence.Column(name = "Index_priv")
    @Basic
    public String getIndexPriv() {
        return indexPriv;
    }

    public void setIndexPriv(String indexPriv) {
        this.indexPriv = indexPriv;
    }

    private String alterPriv;

    @javax.persistence.Column(name = "Alter_priv")
    @Basic
    public String getAlterPriv() {
        return alterPriv;
    }

    public void setAlterPriv(String alterPriv) {
        this.alterPriv = alterPriv;
    }

    private String createTmpTablePriv;

    @javax.persistence.Column(name = "Create_tmp_table_priv")
    @Basic
    public String getCreateTmpTablePriv() {
        return createTmpTablePriv;
    }

    public void setCreateTmpTablePriv(String createTmpTablePriv) {
        this.createTmpTablePriv = createTmpTablePriv;
    }

    private String lockTablesPriv;

    @javax.persistence.Column(name = "Lock_tables_priv")
    @Basic
    public String getLockTablesPriv() {
        return lockTablesPriv;
    }

    public void setLockTablesPriv(String lockTablesPriv) {
        this.lockTablesPriv = lockTablesPriv;
    }

    private String createViewPriv;

    @javax.persistence.Column(name = "Create_view_priv")
    @Basic
    public String getCreateViewPriv() {
        return createViewPriv;
    }

    public void setCreateViewPriv(String createViewPriv) {
        this.createViewPriv = createViewPriv;
    }

    private String showViewPriv;

    @javax.persistence.Column(name = "Show_view_priv")
    @Basic
    public String getShowViewPriv() {
        return showViewPriv;
    }

    public void setShowViewPriv(String showViewPriv) {
        this.showViewPriv = showViewPriv;
    }

    private String createRoutinePriv;

    @javax.persistence.Column(name = "Create_routine_priv")
    @Basic
    public String getCreateRoutinePriv() {
        return createRoutinePriv;
    }

    public void setCreateRoutinePriv(String createRoutinePriv) {
        this.createRoutinePriv = createRoutinePriv;
    }

    private String alterRoutinePriv;

    @javax.persistence.Column(name = "Alter_routine_priv")
    @Basic
    public String getAlterRoutinePriv() {
        return alterRoutinePriv;
    }

    public void setAlterRoutinePriv(String alterRoutinePriv) {
        this.alterRoutinePriv = alterRoutinePriv;
    }

    private String executePriv;

    @javax.persistence.Column(name = "Execute_priv")
    @Basic
    public String getExecutePriv() {
        return executePriv;
    }

    public void setExecutePriv(String executePriv) {
        this.executePriv = executePriv;
    }

    private String eventPriv;

    @javax.persistence.Column(name = "Event_priv")
    @Basic
    public String getEventPriv() {
        return eventPriv;
    }

    public void setEventPriv(String eventPriv) {
        this.eventPriv = eventPriv;
    }

    private String triggerPriv;

    @javax.persistence.Column(name = "Trigger_priv")
    @Basic
    public String getTriggerPriv() {
        return triggerPriv;
    }

    public void setTriggerPriv(String triggerPriv) {
        this.triggerPriv = triggerPriv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbEntity dbEntity = (DbEntity) o;

        if (alterPriv != null ? !alterPriv.equals(dbEntity.alterPriv) : dbEntity.alterPriv != null) return false;
        if (alterRoutinePriv != null ? !alterRoutinePriv.equals(dbEntity.alterRoutinePriv) : dbEntity.alterRoutinePriv != null)
            return false;
        if (createPriv != null ? !createPriv.equals(dbEntity.createPriv) : dbEntity.createPriv != null) return false;
        if (createRoutinePriv != null ? !createRoutinePriv.equals(dbEntity.createRoutinePriv) : dbEntity.createRoutinePriv != null)
            return false;
        if (createTmpTablePriv != null ? !createTmpTablePriv.equals(dbEntity.createTmpTablePriv) : dbEntity.createTmpTablePriv != null)
            return false;
        if (createViewPriv != null ? !createViewPriv.equals(dbEntity.createViewPriv) : dbEntity.createViewPriv != null)
            return false;
        if (db != null ? !db.equals(dbEntity.db) : dbEntity.db != null) return false;
        if (deletePriv != null ? !deletePriv.equals(dbEntity.deletePriv) : dbEntity.deletePriv != null) return false;
        if (dropPriv != null ? !dropPriv.equals(dbEntity.dropPriv) : dbEntity.dropPriv != null) return false;
        if (eventPriv != null ? !eventPriv.equals(dbEntity.eventPriv) : dbEntity.eventPriv != null) return false;
        if (executePriv != null ? !executePriv.equals(dbEntity.executePriv) : dbEntity.executePriv != null)
            return false;
        if (grantPriv != null ? !grantPriv.equals(dbEntity.grantPriv) : dbEntity.grantPriv != null) return false;
        if (host != null ? !host.equals(dbEntity.host) : dbEntity.host != null) return false;
        if (indexPriv != null ? !indexPriv.equals(dbEntity.indexPriv) : dbEntity.indexPriv != null) return false;
        if (insertPriv != null ? !insertPriv.equals(dbEntity.insertPriv) : dbEntity.insertPriv != null) return false;
        if (lockTablesPriv != null ? !lockTablesPriv.equals(dbEntity.lockTablesPriv) : dbEntity.lockTablesPriv != null)
            return false;
        if (referencesPriv != null ? !referencesPriv.equals(dbEntity.referencesPriv) : dbEntity.referencesPriv != null)
            return false;
        if (selectPriv != null ? !selectPriv.equals(dbEntity.selectPriv) : dbEntity.selectPriv != null) return false;
        if (showViewPriv != null ? !showViewPriv.equals(dbEntity.showViewPriv) : dbEntity.showViewPriv != null)
            return false;
        if (triggerPriv != null ? !triggerPriv.equals(dbEntity.triggerPriv) : dbEntity.triggerPriv != null)
            return false;
        if (updatePriv != null ? !updatePriv.equals(dbEntity.updatePriv) : dbEntity.updatePriv != null) return false;
        if (user != null ? !user.equals(dbEntity.user) : dbEntity.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (db != null ? db.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (selectPriv != null ? selectPriv.hashCode() : 0);
        result = 31 * result + (insertPriv != null ? insertPriv.hashCode() : 0);
        result = 31 * result + (updatePriv != null ? updatePriv.hashCode() : 0);
        result = 31 * result + (deletePriv != null ? deletePriv.hashCode() : 0);
        result = 31 * result + (createPriv != null ? createPriv.hashCode() : 0);
        result = 31 * result + (dropPriv != null ? dropPriv.hashCode() : 0);
        result = 31 * result + (grantPriv != null ? grantPriv.hashCode() : 0);
        result = 31 * result + (referencesPriv != null ? referencesPriv.hashCode() : 0);
        result = 31 * result + (indexPriv != null ? indexPriv.hashCode() : 0);
        result = 31 * result + (alterPriv != null ? alterPriv.hashCode() : 0);
        result = 31 * result + (createTmpTablePriv != null ? createTmpTablePriv.hashCode() : 0);
        result = 31 * result + (lockTablesPriv != null ? lockTablesPriv.hashCode() : 0);
        result = 31 * result + (createViewPriv != null ? createViewPriv.hashCode() : 0);
        result = 31 * result + (showViewPriv != null ? showViewPriv.hashCode() : 0);
        result = 31 * result + (createRoutinePriv != null ? createRoutinePriv.hashCode() : 0);
        result = 31 * result + (alterRoutinePriv != null ? alterRoutinePriv.hashCode() : 0);
        result = 31 * result + (executePriv != null ? executePriv.hashCode() : 0);
        result = 31 * result + (eventPriv != null ? eventPriv.hashCode() : 0);
        result = 31 * result + (triggerPriv != null ? triggerPriv.hashCode() : 0);
        return result;
    }
}
