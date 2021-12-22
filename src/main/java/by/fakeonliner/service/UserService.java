package by.fakeonliner.service;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User findByEmail(String email) {
        if (userDao.existByEmail(email)) {
            return userDao.findByEmail(email);
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

//    public boolean existByUsername(String username) {
//        return userDao.existByLogin(username);
//    }

    public void save(User user) {
        userDao.save(user);
    }

    public boolean existByPhoneNumber(String phoneNumber) {
        return userDao.existByPhoneNumber(phoneNumber);
    }

    public boolean existByEmail(String email) {
        return userDao.existByEmail(email);
    }

//    public void changeUsername (long userId, String newUsername){
//        userDao.changeUsername(userId, newUsername);
//    }

    public void changePassword (long userId, String newPassword){
        userDao.changePassword(userId, newPassword);
    }

    public void changePhoneNumber(long userId, String newPhoneNumber){
        userDao.changePhoneNumber(userId, newPhoneNumber);
    }

    public void changeEmail(long userId, String newEmail){
        userDao.changeEmail(userId, newEmail);
    }

    public void changeFirstName(long userId, String newFirstName){
        userDao.changeFirstName(userId, newFirstName);
    }

    public void changeLastName(long userId, String newLastName){
        userDao.changeLastName(userId, newLastName);
    }
}
