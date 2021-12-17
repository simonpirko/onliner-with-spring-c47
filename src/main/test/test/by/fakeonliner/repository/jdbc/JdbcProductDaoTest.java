package test.by.fakeonliner.repository.jdbc;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.repository.jdbc.JdbcProductDao;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcProductDaoTest {

    @Test
    void findByModel() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<ProductDto> samsung = jdbcProductDao.findByModel("iPhone");
        assertNotNull(samsung);
    }

    @Test
    void findByPrice() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<ProductDto> mobile = jdbcProductDao.findByPrice(2500.0, 3064.0, "mobile");
        assertNotNull(mobile);
    }

    @Test
    void delete() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        jdbcProductDao.delete(2);
    }

    @Test
    void existByModel() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        boolean result = jdbcProductDao.existByModel("iPhone");
    }

    @Test
    void findByAllFromCategory() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<ProductDto> list = jdbcProductDao.findByAllFromCategory("mobile");
        System.out.println(list);
    }
}