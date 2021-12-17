package test.by.fakeonliner.repository.jdbc;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.jdbc.JdbcUserDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUserDaoTest {

    @Test
    void save() {
//        User user = new User(1,"FN","LN","UN","PSSW","PN", "EM");
         JdbcUserDao dao = new JdbcUserDao();
//         dao.save(user);
         boolean result = dao.existByLogin("UN");
         assertEquals(true, result);
    }
}