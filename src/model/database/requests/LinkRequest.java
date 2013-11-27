package model.database.requests;

import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardToCardLinkEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 25.11.13
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public class LinkRequest {
    public static ArrayList<CardToCardLinkEntity> getAllCardToCardLink() {
        ArrayList<CardToCardLinkEntity> cardToCardLinkEntities = new ArrayList<CardToCardLinkEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            cardToCardLinkEntities = (ArrayList<CardToCardLinkEntity>) session.createCriteria(CardToCardLinkEntity.class).list();
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cardToCardLinkEntities;
    }

    public static CardToCardLinkEntity getCardToCardLinkByID(Long cardToCardLinkID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            return (CardToCardLinkEntity) session.get(CardToCardLinkEntity.class, cardToCardLinkID);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void addCardToCardLinkRequest(CardToCardLinkEntity cardToCardLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardToCardLinkEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static CardToCardLinkEntity getCardToCardLinkByResultSet(ResultSet rs, String linkRequest) throws SQLException {
        CardToCardLinkEntity cardToCardLinkEntity = null;
        Long cardToCardLinkID = rs.getLong(linkRequest + ".CardToCardLinkID");
        if (cardToCardLinkID != 0 && !rs.wasNull()) {
            cardToCardLinkEntity = new CardToCardLinkEntity();
            cardToCardLinkEntity.setCardToCardLinkID(cardToCardLinkID);
            cardToCardLinkEntity.setCardToCardLinkType(rs.getInt(linkRequest + ".CardToCardLinkType"));
        }
        return cardToCardLinkEntity;
    }
}
