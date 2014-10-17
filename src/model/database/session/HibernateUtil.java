package model.database.session;

import model.constants.Component;
import model.database.worldonlinedb.*;
import model.database.worldonlinedb.dishes.*;
import model.logger.LoggerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static HibernateUtil Instance = new HibernateUtil();
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, HibernateUtil.class);
    private SessionFactory sessionFactory;

    private HibernateUtil() {
        try {
            Configuration ac = buildConfig();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(ac.getProperties()).buildServiceRegistry();
            sessionFactory = ac.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            loggerFactory.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Configuration buildConfig() {
        Configuration ac = new Configuration();
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
                .addAnnotatedClass(TagGroupEntity.class)
                .addAnnotatedClass(CardTagEntity.class)
                .addAnnotatedClass(CardImageEntity.class)
                .addAnnotatedClass(CardRouteEntity.class)
                .addAnnotatedClass(RouteElementEntity.class)
                .addAnnotatedClass(CardCoordinateEntity.class)
                .addAnnotatedClass(RouteCoordinateEntity.class)
                .addAnnotatedClass(ImageEntity.class)
                .addAnnotatedClass(CardToCardLinkEntity.class)
                .addAnnotatedClass(MenuEntity.class)
                .addAnnotatedClass(AdminUserEntity.class)
                .addAnnotatedClass(AdminUserAdditionalInfoEntity.class)
                .addAnnotatedClass(AdminUserSessionEntity.class)
                .addAnnotatedClass(MenuCardLinkEntity.class)
                .addAnnotatedClass(CardChangeEntity.class)
                .addAnnotatedClass(WeatherEntity.class)
                .addAnnotatedClass(ExchangeRatesEntity.class)
                .addAnnotatedClass(UserCardEntity.class)
                .addAnnotatedClass(CardParameterTypeEntity.class)
                .addAnnotatedClass(CardPriceLinkEntity.class)
                .addAnnotatedClass(DishCategoryEntity.class)
                .addAnnotatedClass(DishEntity.class)
                .addAnnotatedClass(DishTagDishLinkEntity.class)
                .addAnnotatedClass(DishTagEntity.class)
                .addAnnotatedClass(UrgencyTimeEntity.class)
                .addAnnotatedClass(CardInformationElementEntity.class)
                .addAnnotatedClass(PriceEntity.class);
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
