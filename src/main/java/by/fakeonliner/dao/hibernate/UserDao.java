package by.fakeonliner.dao.hibernate;


import by.fakeonliner.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void save(User user);
    void delete(User user);
    List<User> getAllUsers();
    User findByEmail(String email);
    boolean existByEmail(String email);
    boolean existByPhoneNumber(String phoneNumber);
    User findById(long id);
    void edit(User user);
}
