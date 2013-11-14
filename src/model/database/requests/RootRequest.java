package model.database.requests;

import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardRootEntity;
import model.database.worldonlinedb.RootElementEntity;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 14.11.13
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class RootRequest {
    public static CardRootEntity getCardRootByResultSet(ResultSet rs) throws SQLException {
        Long cardRootID = rs.getLong("CardRoot.CardRootID");
        if (cardRootID == 0 || rs.wasNull()) {
            return null;
        }
        CardRootEntity cardRootEntity = new CardRootEntity();
        cardRootEntity.setCardRootID(cardRootID);
        cardRootEntity.setCardRootName(rs.getString("CardRoot.CardRootName"));
        return cardRootEntity;
    }

    public static RootElementEntity getRootElementByResultSet(ResultSet rs) throws SQLException {
        Long rootElementID = rs.getLong("RootElement.RootElementID");
        if (rootElementID == 0 || rs.wasNull()) {
            return null;
        }
        RootElementEntity rootElementEntity = new RootElementEntity();
        rootElementEntity.setRootElementID(rootElementID);
        rootElementEntity.setRootElementNumber(rs.getInt("RootElement.RootElementNumber"));
        return rootElementEntity;
    }

    public static void addCardRoot(CardRootEntity cardRootEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardRootEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void addRootElement(RootElementEntity rootElementEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(rootElementEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
