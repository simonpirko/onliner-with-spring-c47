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
    public boolean existByLogin(String userName) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User where username = :un");
        query.setParameter("un", userName);
        return (query.uniqueResult() != null);
    }

    @Override
    public User findByUsername(String userName) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User where username = :un", User.class);
        query.setParameter("un", userName);
        User singleResult = query.uniqueResult();
        return  singleResult;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> resultList = query.getResultList();
        return  resultList;
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
        Query<User> query = session.createQuery("delete User where id = :id");
        query.setParameter("id", user.getId());
        query.executeUpdate();
        session.close();
    }

    @Override
    public boolean existByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User where email = :mail");
        query.setParameter("mail", email);
        return (query.uniqueResult() != null);
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User where phoneNumber = :phone");
        query.setParameter("phone", phoneNumber);
        return (query.uniqueResult() != null);
    }

    @Override
    public void changeFirstName(long userId, String newFirstName) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set firstName = :name"+
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("name", newFirstName);
        session.update(query);
        session.close();
    }

    @Override
    public void changeLastName(long userId, String newLastName) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set lastName = :lastname"+
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("lastname", newLastName);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void changeUsername(long userId, String newUsername) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set username = :un"+
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("un", newUsername);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void changePassword(long userId, String newPassword) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set password = :pw"+
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("pw", newPassword);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void changePhoneNumber(long userId, String newPhoneNumber) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set phoneNumber = :pn"+
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("pn", newPhoneNumber);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void changeEmail(long userId, String newEmail) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("update User set email = :mail"+
                " where id = :id");
        query.setParameter("id", userId);
        query.setParameter("mail", newEmail);
        query.executeUpdate();
        session.close();
    }
}
