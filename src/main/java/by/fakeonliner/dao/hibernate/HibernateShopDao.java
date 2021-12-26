package by.fakeonliner.dao.hibernate;


import by.fakeonliner.dao.ShopDao;
import by.fakeonliner.entity.shop.Shop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateShopDao implements ShopDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Shop shop) {
        try (Session session = sessionFactory.openSession()) {
            session.save(shop);
        } catch (NoResultException e) {
            return;
        }
    }

    @Override
    public boolean existByEmail(String email) {
        try (Session session = sessionFactory.getCurrentSession()) {
            boolean exist = session.createQuery("select count(*) from Shop where email= :email")
                    .setParameter("email", email).list().isEmpty();
            return exist;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Shop getShopByEmail(String email) {
        try (Session session = sessionFactory.getCurrentSession()) {
            return (Shop) session.createQuery("from Shop where email= :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Shop> getShopList() {
        try (Session session = sessionFactory.getCurrentSession()) {
            return session.createQuery("from Shop", Shop.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void edit(Shop shop) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.update(shop);
        } catch (NoResultException e) {
            return;
        }
    }

    @Override
    public void delete(Shop shop) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.delete(shop);
        } catch (NoResultException e) {
            return;
        }
    }

    @Override
    public Shop findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from Shop where id = :id", Shop.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
