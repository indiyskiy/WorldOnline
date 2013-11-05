package model.database.requests;

import model.constants.databaseenumeration.TagType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardTagEntity;
import model.database.worldonlinedb.TagEntity;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 01.11.13
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public class TagRequest {
    public static CardTagEntity getCardTagByResultSet(ResultSet rs) throws SQLException {
        CardTagEntity cardTagEntity = null;
        if (rs.getLong("CardTag.CardTagID") != 0 && !rs.wasNull()) {
            cardTagEntity = new CardTagEntity();
            cardTagEntity.setCardTagID(rs.getLong("CardTag.CardTagID"));
        }
        return cardTagEntity;
    }

    public static TagEntity getTagByResultSet(ResultSet rs) throws SQLException {
        TagEntity tagEntity = null;
        if (rs.getLong("Tag.TagID") != 0 && !rs.wasNull()) {
            tagEntity = new TagEntity();
            tagEntity.setTagID(rs.getLong("Tag.TagID"));
            tagEntity.setTagName(rs.getString("Tag.TagName"));
            tagEntity.setTagType(rs.getInt("Tag.TagType"));
        }
        return tagEntity;
    }

    public static TagEntity getTag(TagType tagType, String tagName) throws SQLException {
        TagEntity tag = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = "SELECT * FROM Tag " +
                    "WHERE Tag.TagType=? AND Tag.TagName=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, tagType.getValue());
            ps.setString(2, tagName);
            rs = ps.executeQuery();
            if (rs.first()) {
                tag = getTagByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return tag;
    }

    public static void addCardTag(CardTagEntity cardTagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardTagEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}