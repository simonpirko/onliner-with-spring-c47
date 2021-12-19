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

@Repository
@Transactional
public class JdbcBasketDao implements BasketDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addProduct(BasketProductDto product) {
        Session session = sessionFactory.openSession();
        session.save(product);
        session.close();
    }

    @Override
    public void deleteProductDb(BasketProductDto product) {
        Session session = sessionFactory.openSession();
        session.delete(product);
        session.close();
    }

    @Override
    public List<BasketProductDto> getBasketProducts(User user) {
        Session session = sessionFactory.openSession();
        Query<BasketProductDto> query = session.createQuery("from BasketProductDto where user.id = :id", BasketProductDto.class);
        query.setParameter("id", user.getId());
        List<BasketProductDto> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public int getProductAmount(BasketProductDto product) {
        Session session = sessionFactory.openSession();
        Query<BasketProductDto> query = session.createQuery("from BasketProductDto where" +
                " user.id = :userId and product.id = :productId", BasketProductDto.class);
        query.setParameter("userId", product.getUser().getId());
        query.setParameter("productId", product.getProduct().getId());
        BasketProductDto basketProductDto = query.getSingleResult();
        session.close();
        return basketProductDto.getAmount();
    }

    @Override
    public void updateProduct(BasketProductDto product) {
        Session session = sessionFactory.openSession();
        session.update(product);
        session.close();
    }

    @Override
    public boolean existByProductDto(BasketProductDto product) {
        Session session = sessionFactory.openSession();
        Query<BasketProductDto> query = session.createQuery("select 1 from BasketProductDto where user.id = :userId and product.id = :productId");
        query.setParameter("userId", product.getUser().getId());
        query.setParameter("productId", product.getUser().getId());
        return (query.uniqueResult() != null);
    }
}
