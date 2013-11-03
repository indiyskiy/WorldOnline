package model.database.requests;

import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 02.11.13
 * Time: 2:49
 * To change this template use File | Settings | File Templates.
 */
public class TextRequest {

    public static TextGroupEntity getTextGroupByResultSet(ResultSet rs, String tagTextGroup) throws SQLException {
        TextGroupEntity textGroup = null;
        if (rs.getLong(tagTextGroup+".TextGroupID") != 0 && !rs.wasNull()) {
            textGroup = new TextGroupEntity();
            textGroup.setTextGroupID(rs.getLong("TextGroupID"));
            textGroup.setTextGroupName(rs.getString("CardGroupName"));
        }
        return textGroup;
    }

    public static TextEntity getTextByResultSet(ResultSet rs, String tagText) throws SQLException {
        TextEntity textEntity=null;
        if(rs.getLong(tagText+".TextID")!=0 && !rs.wasNull()){
            textEntity=new TextEntity();
            textEntity.setTextID(rs.getLong(tagText+".TextID"));
            textEntity.setLanguageID(rs.getInt(tagText+".LanguageID"));
            textEntity.setText(rs.getString(tagText+".Text"));
        }
        return textEntity;
    }
}
