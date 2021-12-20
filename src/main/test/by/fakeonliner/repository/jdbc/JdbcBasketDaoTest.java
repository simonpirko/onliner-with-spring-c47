package by.fakeonliner.repository.jdbc;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.entity.user.RoleUser;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.BasketDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcBasketDaoTest {

    BasketDao jdbcBasket = new JdbcBasketDao();
    User user = new User(1, "test", "test", "test", "test", "test", "123", RoleUser.USER);
    ProductDto productDto = new ProductDto(1, "test", 123, "test", 123, 123, "test123", "test");

    @Test
    void addProduct() {
        BasketProductDto product = new BasketProductDto(user, productDto);
        jdbcBasket.addProduct(product);
        boolean productDb = jdbcBasket.existByProductDto(product);
        assertEquals(true, productDb);
    }

    @Test
    void deleteProductDb() {
    }

    @Test
    void getBasketProducts() {
    }

    @Test
    void getProductAmount() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void existByProductDto() {
    }
}