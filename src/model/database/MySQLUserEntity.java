package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.MySQLUserEntityPK.class)
@javax.persistence.Table(name = "user", schema = "", catalog = "mysql")
@Entity
public class MySQLUserEntity {
    private String host;

    @javax.persistence.Column(name = "Host")
    @Id
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    private String password;

    @javax.persistence.Column(name = "Password")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    private String reloadPriv;

    @javax.persistence.Column(name = "Reload_priv")
    @Basic
    public String getReloadPriv() {
        return reloadPriv;
    }

    public void setReloadPriv(String reloadPriv) {
        this.reloadPriv = reloadPriv;
    }

    private String shutdownPriv;

    @javax.persistence.Column(name = "Shutdown_priv")
    @Basic
    public String getShutdownPriv() {
        return shutdownPriv;
    }

    public void setShutdownPriv(String shutdownPriv) {
        this.shutdownPriv = shutdownPriv;
    }

    private String processPriv;

    @javax.persistence.Column(name = "Process_priv")
    @Basic
    public String getProcessPriv() {
        return processPriv;
    }

    public void setProcessPriv(String processPriv) {
        this.processPriv = processPriv;
    }

    private String filePriv;

    @javax.persistence.Column(name = "File_priv")
    @Basic
    public String getFilePriv() {
        return filePriv;
    }

    public void setFilePriv(String filePriv) {
        this.filePriv = filePriv;
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

    private String showDbPriv;

    @javax.persistence.Column(name = "Show_db_priv")
    @Basic
    public String getShowDbPriv() {
        return showDbPriv;
    }

    public void setShowDbPriv(String showDbPriv) {
        this.showDbPriv = showDbPriv;
    }

    private String superPriv;

    @javax.persistence.Column(name = "Super_priv")
    @Basic
    public String getSuperPriv() {
        return superPriv;
    }

    public void setSuperPriv(String superPriv) {
        this.superPriv = superPriv;
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

    private String executePriv;

    @javax.persistence.Column(name = "Execute_priv")
    @Basic
    public String getExecutePriv() {
        return executePriv;
    }

    public void setExecutePriv(String executePriv) {
        this.executePriv = executePriv;
    }

    private String replSlavePriv;

    @javax.persistence.Column(name = "Repl_slave_priv")
    @Basic
    public String getReplSlavePriv() {
        return replSlavePriv;
    }

    public void setReplSlavePriv(String replSlavePriv) {
        this.replSlavePriv = replSlavePriv;
    }

    private String replClientPriv;

    @javax.persistence.Column(name = "Repl_client_priv")
    @Basic
    public String getReplClientPriv() {
        return replClientPriv;
    }

    public void setReplClientPriv(String replClientPriv) {
        this.replClientPriv = replClientPriv;
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

    private String createUserPriv;

    @javax.persistence.Column(name = "Create_user_priv")
    @Basic
    public String getCreateUserPriv() {
        return createUserPriv;
    }

    public void setCreateUserPriv(String createUserPriv) {
        this.createUserPriv = createUserPriv;
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

    private String createTablespacePriv;

    @javax.persistence.Column(name = "Create_tablespace_priv")
    @Basic
    public String getCreateTablespacePriv() {
        return createTablespacePriv;
    }

    public void setCreateTablespacePriv(String createTablespacePriv) {
        this.createTablespacePriv = createTablespacePriv;
    }

    private String sslType;

    @javax.persistence.Column(name = "ssl_type")
    @Basic
    public String getSslType() {
        return sslType;
    }

    public void setSslType(String sslType) {
        this.sslType = sslType;
    }

    private byte[] sslCipher;

    @javax.persistence.Column(name = "ssl_cipher")
    @Basic
    public byte[] getSslCipher() {
        return sslCipher;
    }

    public void setSslCipher(byte[] sslCipher) {
        this.sslCipher = sslCipher;
    }

    private byte[] x509Issuer;

    @javax.persistence.Column(name = "x509_issuer")
    @Basic
    public byte[] getX509Issuer() {
        return x509Issuer;
    }

    public void setX509Issuer(byte[] x509Issuer) {
        this.x509Issuer = x509Issuer;
    }

    private byte[] x509Subject;

    @javax.persistence.Column(name = "x509_subject")
    @Basic
    public byte[] getX509Subject() {
        return x509Subject;
    }

    public void setX509Subject(byte[] x509Subject) {
        this.x509Subject = x509Subject;
    }

    private int maxQuestions;

    @javax.persistence.Column(name = "max_questions")
    @Basic
    public int getMaxQuestions() {
        return maxQuestions;
    }

    public void setMaxQuestions(int maxQuestions) {
        this.maxQuestions = maxQuestions;
    }

    private int maxUpdates;

    @javax.persistence.Column(name = "max_updates")
    @Basic
    public int getMaxUpdates() {
        return maxUpdates;
    }

    public void setMaxUpdates(int maxUpdates) {
        this.maxUpdates = maxUpdates;
    }

    private int maxConnections;

    @javax.persistence.Column(name = "max_connections")
    @Basic
    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    private int maxUserConnections;

    @javax.persistence.Column(name = "max_user_connections")
    @Basic
    public int getMaxUserConnections() {
        return maxUserConnections;
    }

    public void setMaxUserConnections(int maxUserConnections) {
        this.maxUserConnections = maxUserConnections;
    }

    private String plugin;

    @javax.persistence.Column(name = "plugin")
    @Basic
    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    private String authenticationString;

    @javax.persistence.Column(name = "authentication_string")
    @Basic
    public String getAuthenticationString() {
        return authenticationString;
    }

    public void setAuthenticationString(String authenticationString) {
        this.authenticationString = authenticationString;
    }

    private String passwordExpired;

    @javax.persistence.Column(name = "password_expired")
    @Basic
    public String getPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(String passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MySQLUserEntity that = (MySQLUserEntity) o;

        if (maxConnections != that.maxConnections) return false;
        if (maxQuestions != that.maxQuestions) return false;
        if (maxUpdates != that.maxUpdates) return false;
        if (maxUserConnections != that.maxUserConnections) return false;
        if (alterPriv != null ? !alterPriv.equals(that.alterPriv) : that.alterPriv != null) return false;
        if (alterRoutinePriv != null ? !alterRoutinePriv.equals(that.alterRoutinePriv) : that.alterRoutinePriv != null)
            return false;
        if (authenticationString != null ? !authenticationString.equals(that.authenticationString) : that.authenticationString != null)
            return false;
        if (createPriv != null ? !createPriv.equals(that.createPriv) : that.createPriv != null) return false;
        if (createRoutinePriv != null ? !createRoutinePriv.equals(that.createRoutinePriv) : that.createRoutinePriv != null)
            return false;
        if (createTablespacePriv != null ? !createTablespacePriv.equals(that.createTablespacePriv) : that.createTablespacePriv != null)
            return false;
        if (createTmpTablePriv != null ? !createTmpTablePriv.equals(that.createTmpTablePriv) : that.createTmpTablePriv != null)
            return false;
        if (createUserPriv != null ? !createUserPriv.equals(that.createUserPriv) : that.createUserPriv != null)
            return false;
        if (createViewPriv != null ? !createViewPriv.equals(that.createViewPriv) : that.createViewPriv != null)
            return false;
        if (deletePriv != null ? !deletePriv.equals(that.deletePriv) : that.deletePriv != null) return false;
        if (dropPriv != null ? !dropPriv.equals(that.dropPriv) : that.dropPriv != null) return false;
        if (eventPriv != null ? !eventPriv.equals(that.eventPriv) : that.eventPriv != null) return false;
        if (executePriv != null ? !executePriv.equals(that.executePriv) : that.executePriv != null) return false;
        if (filePriv != null ? !filePriv.equals(that.filePriv) : that.filePriv != null) return false;
        if (grantPriv != null ? !grantPriv.equals(that.grantPriv) : that.grantPriv != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (indexPriv != null ? !indexPriv.equals(that.indexPriv) : that.indexPriv != null) return false;
        if (insertPriv != null ? !insertPriv.equals(that.insertPriv) : that.insertPriv != null) return false;
        if (lockTablesPriv != null ? !lockTablesPriv.equals(that.lockTablesPriv) : that.lockTablesPriv != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (passwordExpired != null ? !passwordExpired.equals(that.passwordExpired) : that.passwordExpired != null)
            return false;
        if (plugin != null ? !plugin.equals(that.plugin) : that.plugin != null) return false;
        if (processPriv != null ? !processPriv.equals(that.processPriv) : that.processPriv != null) return false;
        if (referencesPriv != null ? !referencesPriv.equals(that.referencesPriv) : that.referencesPriv != null)
            return false;
        if (reloadPriv != null ? !reloadPriv.equals(that.reloadPriv) : that.reloadPriv != null) return false;
        if (replClientPriv != null ? !replClientPriv.equals(that.replClientPriv) : that.replClientPriv != null)
            return false;
        if (replSlavePriv != null ? !replSlavePriv.equals(that.replSlavePriv) : that.replSlavePriv != null)
            return false;
        if (selectPriv != null ? !selectPriv.equals(that.selectPriv) : that.selectPriv != null) return false;
        if (showDbPriv != null ? !showDbPriv.equals(that.showDbPriv) : that.showDbPriv != null) return false;
        if (showViewPriv != null ? !showViewPriv.equals(that.showViewPriv) : that.showViewPriv != null) return false;
        if (shutdownPriv != null ? !shutdownPriv.equals(that.shutdownPriv) : that.shutdownPriv != null) return false;
        if (!Arrays.equals(sslCipher, that.sslCipher)) return false;
        if (sslType != null ? !sslType.equals(that.sslType) : that.sslType != null) return false;
        if (superPriv != null ? !superPriv.equals(that.superPriv) : that.superPriv != null) return false;
        if (triggerPriv != null ? !triggerPriv.equals(that.triggerPriv) : that.triggerPriv != null) return false;
        if (updatePriv != null ? !updatePriv.equals(that.updatePriv) : that.updatePriv != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (!Arrays.equals(x509Issuer, that.x509Issuer)) return false;
        if (!Arrays.equals(x509Subject, that.x509Subject)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (selectPriv != null ? selectPriv.hashCode() : 0);
        result = 31 * result + (insertPriv != null ? insertPriv.hashCode() : 0);
        result = 31 * result + (updatePriv != null ? updatePriv.hashCode() : 0);
        result = 31 * result + (deletePriv != null ? deletePriv.hashCode() : 0);
        result = 31 * result + (createPriv != null ? createPriv.hashCode() : 0);
        result = 31 * result + (dropPriv != null ? dropPriv.hashCode() : 0);
        result = 31 * result + (reloadPriv != null ? reloadPriv.hashCode() : 0);
        result = 31 * result + (shutdownPriv != null ? shutdownPriv.hashCode() : 0);
        result = 31 * result + (processPriv != null ? processPriv.hashCode() : 0);
        result = 31 * result + (filePriv != null ? filePriv.hashCode() : 0);
        result = 31 * result + (grantPriv != null ? grantPriv.hashCode() : 0);
        result = 31 * result + (referencesPriv != null ? referencesPriv.hashCode() : 0);
        result = 31 * result + (indexPriv != null ? indexPriv.hashCode() : 0);
        result = 31 * result + (alterPriv != null ? alterPriv.hashCode() : 0);
        result = 31 * result + (showDbPriv != null ? showDbPriv.hashCode() : 0);
        result = 31 * result + (superPriv != null ? superPriv.hashCode() : 0);
        result = 31 * result + (createTmpTablePriv != null ? createTmpTablePriv.hashCode() : 0);
        result = 31 * result + (lockTablesPriv != null ? lockTablesPriv.hashCode() : 0);
        result = 31 * result + (executePriv != null ? executePriv.hashCode() : 0);
        result = 31 * result + (replSlavePriv != null ? replSlavePriv.hashCode() : 0);
        result = 31 * result + (replClientPriv != null ? replClientPriv.hashCode() : 0);
        result = 31 * result + (createViewPriv != null ? createViewPriv.hashCode() : 0);
        result = 31 * result + (showViewPriv != null ? showViewPriv.hashCode() : 0);
        result = 31 * result + (createRoutinePriv != null ? createRoutinePriv.hashCode() : 0);
        result = 31 * result + (alterRoutinePriv != null ? alterRoutinePriv.hashCode() : 0);
        result = 31 * result + (createUserPriv != null ? createUserPriv.hashCode() : 0);
        result = 31 * result + (eventPriv != null ? eventPriv.hashCode() : 0);
        result = 31 * result + (triggerPriv != null ? triggerPriv.hashCode() : 0);
        result = 31 * result + (createTablespacePriv != null ? createTablespacePriv.hashCode() : 0);
        result = 31 * result + (sslType != null ? sslType.hashCode() : 0);
        result = 31 * result + (sslCipher != null ? Arrays.hashCode(sslCipher) : 0);
        result = 31 * result + (x509Issuer != null ? Arrays.hashCode(x509Issuer) : 0);
        result = 31 * result + (x509Subject != null ? Arrays.hashCode(x509Subject) : 0);
        result = 31 * result + maxQuestions;
        result = 31 * result + maxUpdates;
        result = 31 * result + maxConnections;
        result = 31 * result + maxUserConnections;
        result = 31 * result + (plugin != null ? plugin.hashCode() : 0);
        result = 31 * result + (authenticationString != null ? authenticationString.hashCode() : 0);
        result = 31 * result + (passwordExpired != null ? passwordExpired.hashCode() : 0);
        return result;
    }
}
