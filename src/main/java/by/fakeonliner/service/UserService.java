package by.fakeonliner.service;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.jdbc.JdbcUserDao;

import java.util.List;

public class UserService {
    private final JdbcUserDao jdbcUserDao =new JdbcUserDao();
    private static UserService instance;

    private UserService(){};

    public static synchronized UserService getInstance() {
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public User findByUsername(String userName) {
        if (jdbcUserDao.existByLogin(userName)) {
            return jdbcUserDao.findByUsername(userName);
        }
        return null;
    }

    public List<User> getAllUsers() {
        return jdbcUserDao.getAllUsers();
    }

    public boolean existByUsername(String username) {
        return jdbcUserDao.existByLogin(username);
    }

    public void save(User user) {
        jdbcUserDao.save(user);
    }

    public boolean existByPhoneNumber(String phoneNumber) {
        return jdbcUserDao.existByPhoneNumber(phoneNumber);
    }

    public boolean existByEmail(String email) {
        return jdbcUserDao.existByEmail(email);
    }

    public void changeUsername (long userId, String newUsername){
        jdbcUserDao.changeUsername(userId, newUsername);
    }

    public void changePassword (long userId, String newPassword){
        jdbcUserDao.changePassword(userId, newPassword);
    }

    public void changePhoneNumber(long userId, String newPhoneNumber){
        jdbcUserDao.changePhoneNumber(userId, newPhoneNumber);
    }

    public void changeEmail(long userId, String newEmail){
        jdbcUserDao.changeEmail(userId, newEmail);
    }

    public void changeFirstName(long userId, String newFirstName){
        jdbcUserDao.changeFirstName(userId, newFirstName);
    }

    public void changeLastName(long userId, String newLastName){
        jdbcUserDao.changeLastName(userId, newLastName);
    }
}
