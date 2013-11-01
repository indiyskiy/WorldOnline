package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "SESSION_VARIABLES", schema = "", catalog = "information_schema")
@Entity
public class SessionVariablesEntity {
    private String variableName;

    @javax.persistence.Column(name = "VARIABLE_NAME")
    @Basic
    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    private String variableValue;

    @javax.persistence.Column(name = "VARIABLE_VALUE")
    @Basic
    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionVariablesEntity that = (SessionVariablesEntity) o;

        if (variableName != null ? !variableName.equals(that.variableName) : that.variableName != null) return false;
        if (variableValue != null ? !variableValue.equals(that.variableValue) : that.variableValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = variableName != null ? variableName.hashCode() : 0;
        result = 31 * result + (variableValue != null ? variableValue.hashCode() : 0);
        return result;
    }
}
