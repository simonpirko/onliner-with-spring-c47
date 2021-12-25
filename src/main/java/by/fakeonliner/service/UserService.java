package by.fakeonliner.service;

import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private HibernateUserDao hibernateUserDao;

    public void save(User user) {
        hibernateUserDao.save(user);
    }

    public void delete(User user) {
        hibernateUserDao.delete(user);
    }

    public List<User> getAllUsers() {
        return hibernateUserDao.getAllUsers();
    }

    public User findByEmail(String email) {
        return hibernateUserDao.findByEmail(email);
    }

    public boolean existByEmail(String email) {
        return hibernateUserDao.existByEmail(email);
    }

    public boolean existByPhoneNumber(String phoneNumber) {
        return hibernateUserDao.existByPhoneNumber(phoneNumber);
    }


    public User findById(long id) {
        return hibernateUserDao.findById(id);
    }

    public void edit(User user) {
        hibernateUserDao.edit(user);
    }
}
