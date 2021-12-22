package by.fakeonliner.repository;

import by.fakeonliner.entity.user.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void save(User user);

    void delete(User user);

    User findByEmail(String email);

    boolean existByEmail(String email);

    boolean existByPhoneNumber(String phoneNumber);

    void changeFirstName(long userId, String newFirstName);

    void changeLastName(long userId, String newLastName);

    void changePassword(long userId, String newPassword);

    void changePhoneNumber(long userId, String newPhoneNumber);

    void changeEmail(long userId, String newEmail);
}
