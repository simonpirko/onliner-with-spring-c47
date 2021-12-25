package by.fakeonliner.dao.hibernate;


import by.fakeonliner.entity.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HibernateUserDao implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        return false;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public void edit(User user) {

    }
}
