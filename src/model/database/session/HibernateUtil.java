package model.database.session;

import model.database.worldonlinedb.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:51
 * To change this template use File | Settings | File Templates.
 */


public class HibernateUtil {
    private static HibernateUtil Instance = new HibernateUtil();
    private SessionFactory sessionFactory;

    private HibernateUtil() {
        try {
            Configuration ac = new Configuration();
//            Configuration ac=new Configuration().configure();
            ac.addAnnotatedClass(UserEntity.class)
                    .addAnnotatedClass(GlobalVersionEntity.class)
                    .addAnnotatedClass(UserHardwareEntity.class)
                    .addAnnotatedClass(UserContentEntity.class)
                    .addAnnotatedClass(UserPersonalDataEntity.class)
                    .addAnnotatedClass(CardEntity.class)
                    .addAnnotatedClass(CardParameterEntity.class)
                    .addAnnotatedClass(TextCardEntity.class)
                    .addAnnotatedClass(TextGroupEntity.class)
                    .addAnnotatedClass(TextEntity.class)
                    .addAnnotatedClass(TagEntity.class)
                    .addAnnotatedClass(CardTagEntity.class)
                    .addAnnotatedClass(CardImageEntity.class)
                    .addAnnotatedClass(CardRootEntity.class)
                    .addAnnotatedClass(RootElementEntity.class)
                    .addAnnotatedClass(CardCoordinateEntity.class)
                    .addAnnotatedClass(ImageEntity.class);
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            Configuration conf = ac.configure();
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static HibernateUtil getInstance() {
        return Instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
