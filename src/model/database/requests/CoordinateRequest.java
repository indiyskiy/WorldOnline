package model.database.requests;

import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardCoordinateEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 17.12.13
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */
public class CoordinateRequest {
    public static boolean addCardCoordinate(CardCoordinateEntity cardCoordinateEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            session.save(cardCoordinateEntity);
            session.getTransaction().commit();
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
