package model.database.requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.database.worldonlinedb.CardParameterEntity;
/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 07.11.13
 * Time: 8:10
 * To change this template use File | Settings | File Templates.
 */
public class ParameterRequest {
    public static CardParameterEntity getCardParameterByResultSet(ResultSet rs) throws SQLException {
        Long  cardParameterEntityID = rs.getLong("CardParameter.CardParameterID");
        CardParameterEntity cardParameterEntity=null;
        if(cardParameterEntityID!=0 && !rs.wasNull()){
            cardParameterEntity=new CardParameterEntity();
            cardParameterEntity.setCardParameterID(cardParameterEntityID);
            cardParameterEntity.setCardParameterDataType(rs.getInt("CardParameter.CardParameterDataType"));
            cardParameterEntity.setCardParameterName(rs.getString("CardParameter.CardParameterName"));
            cardParameterEntity.setCardParameterType(rs.getInt("CardParameter.CardParameterType"));
            cardParameterEntity.setCardParameterValue(rs.getString(rs.getString("CardParameter.CardParameterValue")));
        }
        return cardParameterEntity;
    }
}
