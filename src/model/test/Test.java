package model.test;

import model.database.requests.UserRequests;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:23
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            UserEntity userEntity = (UserEntity) session.get(UserEntity.class, 1);
//            System.out.println(userEntity.getUserVersion().getCreatingTime());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        System.out.println(UserRequests.getAllUsers().size());
    }
}
