package model.database.requests;

import model.constants.Component;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.dishes.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;

public class DishRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, DishRequest.class);

    public static boolean addDishTag(DishTagEntity dishTagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishTagEntity);
            session.getTransaction().commit();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addPrice(PriceEntity priceEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(priceEntity);
            session.getTransaction().commit();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addCardPriceLink(CardPriceLinkEntity cardPriceLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(cardPriceLinkEntity);
            session.getTransaction().commit();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addDishTagDishLink(DishTagDishLinkEntity dishTagDishLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishTagDishLinkEntity);
            session.getTransaction().commit();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addDish(DishEntity dishEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishEntity);
            session.getTransaction().commit();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addDishCategory(DishCategoryEntity dishCategoryEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishCategoryEntity);
            session.getTransaction().commit();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }
}
