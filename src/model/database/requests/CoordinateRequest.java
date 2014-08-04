package model.database.requests;

import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardCoordinateEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CoordinateRequest {
    public static boolean addCardCoordinate(CardCoordinateEntity cardCoordinateEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Transaction transaction = null;
        try {
            session.beginTransaction();
            session.save(cardCoordinateEntity);
            session.getTransaction().commit();
            return true;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
