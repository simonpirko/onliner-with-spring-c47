package by.fakeonliner.dao.hibernate;

import by.fakeonliner.dao.ProductDao;
import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.DescriptionFeature;
import by.fakeonliner.entity.product.DescriptionFeatureValue;
import by.fakeonliner.entity.product.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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
            return;
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
            return session.createQuery("from Product where model = :pm", Product.class)
                    .setParameter("pm", model)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Product> findByCategoryId(long categoryId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product p where p.categoryId = :ci")
                    .setParameter("ci", categoryId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Product> findByPrice(double min, double max, long categoryId) {
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
            return;
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            Product product = session.find(Product.class, id);
            session.delete(product);
        } catch (NoResultException e) {
            return;
        }

    }

    @Override
    public Product findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Product.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }
}
