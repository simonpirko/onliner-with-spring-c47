package by.fakeonliner.dao.hibernate;


import by.fakeonliner.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
public class HibernateUserDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        } catch (NoResultException e) {
            return;
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.delete(user);
        } catch (NoResultException e) {
            return;
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User where email = :mail", User.class)
                    .setParameter("mail", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean existByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            boolean exist = session.createQuery("from User where email = :mail")
                    .setParameter("mail", email)
                    .list().isEmpty();
            return exist;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            boolean exist = session.createQuery("from User where phoneNumber = :phone")
                    .setParameter("phone", phoneNumber).list().isEmpty();
            return exist;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public User findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from User where id = :id", User.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void edit(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.update(user);
        } catch (NoResultException e) {
            return;
        }
    }
}
