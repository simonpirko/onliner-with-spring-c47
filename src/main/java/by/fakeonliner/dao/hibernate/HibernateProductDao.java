package by.fakeonliner.dao.hibernate;

import by.fakeonliner.dao.ProductDao;
import by.fakeonliner.dto.product.DescriptionFeatureValueDto;
import by.fakeonliner.entity.product.Product;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HibernateProductDao implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.save(product);
        } catch (NoResultException e) {
        }
    }

    @Override
    public boolean existByModel(String model) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product where model = :mo")
                    .setParameter("mo", model).uniqueResult() != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public List<Product> findByModel(String model) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product where lower (model) LIKE lower(:md)", Product.class)
                    .setParameter("md", "%" + model + "%")
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List findByCategoryId(long categoryId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product p where p.categoryId = :ci")
                    .setParameter("ci", categoryId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List findByPrice(double min, double max, long categoryId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product p where p.price > :min and price < :max and p.categoryId = :categoryId")
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .setParameter("categoryId", categoryId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void edit(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(product);
        } catch (NoResultException e) {
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            Product product = session.find(Product.class, id);
            session.delete(product);
        } catch (NoResultException e) {
        }

    }

    @Override
    public Product findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Product product = session.find(Product.class, id);
            Hibernate.initialize(product.getDescriptionFeatureValues()); //Для получения ленивого объекта вне транзакции
            return product;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product").list();
        }
    }

    @Override
    public void saveDescriptionFeatureValues(DescriptionFeatureValueDto descriptionFeatureValueDto) {
        try (Session session = sessionFactory.openSession()) {
            session.save(descriptionFeatureValueDto);
        } catch (NoResultException e) {
        }
    }
}
