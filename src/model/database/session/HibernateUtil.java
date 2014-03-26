package model.database.session;

import model.constants.Component;
import model.database.worldonlinedb.*;
import model.logger.LoggerFactory;
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
    private final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, HibernateUtil.class);
    private SessionFactory sessionFactory;

    private HibernateUtil() {
//    public HibernateUtil() {
        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//            configuration.setNamingStrategy(new ImprovedNamingStrategy());
//            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            Configuration ac = buildConfig();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(ac.getProperties()).buildServiceRegistry();
            sessionFactory = ac.buildSessionFactory(serviceRegistry);
//            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            loggerFactory.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Configuration buildConfig() {
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
                .addAnnotatedClass(ImageEntity.class)
                .addAnnotatedClass(CardToCardLinkEntity.class)
                .addAnnotatedClass(MenuEntity.class)
                .addAnnotatedClass(AdminUserEntity.class)
                .addAnnotatedClass(MenuCardLinkEntity.class)
                .addAnnotatedClass(UserCardEntity.class)
                .addAnnotatedClass(AdminRoleEntity.class);
        ac.configure();
        return ac;
    }

    public static HibernateUtil getInstance() {
        return Instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
