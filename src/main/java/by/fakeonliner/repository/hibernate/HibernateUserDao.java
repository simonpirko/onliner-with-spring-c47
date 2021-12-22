package by.fakeonliner.repository.hibernate;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HibernateUserDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> list = session.createQuery("from User", User.class)
                .getResultList();
        session.close();
        return list;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        session.delete(user);
        session.close();
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.openSession();
        User user = session.createQuery("from User where email = :mail", User.class)
                .setParameter("mail", email).getSingleResult();
        session.close();
        return user;
    }

    @Override
    public boolean existByEmail(String email) {
        Session session = sessionFactory.openSession();
        boolean exist = session.createQuery("from User where email = :mail")
                .setParameter("mail", email)
                .list().isEmpty();
        session.close();
        return exist;
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.openSession();
        boolean exist = session.createQuery("from User where phoneNumber = :phone")
                .setParameter("phone", phoneNumber).list().isEmpty();
        session.close();
        return exist;
    }

    @Override
    public void changeFirstName(long userId, String newFirstName) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set firstName = :name" +
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("name", newFirstName);
        session.update(query);
        session.close();
    }

    @Override
    public void changeLastName(long userId, String newLastName) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set lastName = :lastname" +
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("lastname", newLastName);
        query.executeUpdate();
        session.close();
    }


    @Override
    public void changePassword(long userId, String newPassword) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set password = :pw" +
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("pw", newPassword);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void changePhoneNumber(long userId, String newPhoneNumber) {
        Session session = sessionFactory.openSession();
        session.createQuery("update User set phoneNumber = :pn" +
                        " where id = :id")
                .setParameter("id", userId)
                .setParameter("pn", newPhoneNumber)
                .executeUpdate();
        session.close();
    }

    @Override
    public void changeEmail(long userId, String newEmail) {
        Session session = sessionFactory.openSession();
        session.createQuery("update User set email = :mail" +
                        " where id = :id")
                .setParameter("id", userId)
                .setParameter("mail", newEmail).executeUpdate();
        session.close();
    }
}
