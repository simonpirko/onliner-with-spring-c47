package by.fakeonliner.repository.jdbc;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.BasketDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public class JdbcBasketDao implements BasketDao {

    @Override
    public void addProduct(BasketProductDto product) {

    }

    @Override
    public void deleteProductDb(BasketProductDto product) {

    }

    @Override
    public List<BasketProductDto> getBasketProducts(User user) {
        return null;
    }

    @Override
    public int getProductAmount(BasketProductDto product) {
        return 0;
    }

    @Override
    public void updateProduct(BasketProductDto product) {

    }

    @Override
    public boolean existByProductDto(BasketProductDto product) {
        return false;
    }
}
