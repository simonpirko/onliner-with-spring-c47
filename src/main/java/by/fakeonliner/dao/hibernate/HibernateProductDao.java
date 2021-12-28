package by.fakeonliner.dao.hibernate;

import by.fakeonliner.dao.ProductDao;
import by.fakeonliner.entity.product.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateProductDao implements ProductDao<Product> {

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
            boolean exist = session.createQuery("from Product where model = :mo")
                    .setParameter("mo", model).uniqueResult() != null;
            return exist;
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
    public List<Product> getByCategoryId(long categoryId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product p where p.categoryId = :ci")
                    .setParameter("ci", categoryId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Product> findByAllFromCategory(String category) {
        try (Session session = sessionFactory.openSession()) {

        } catch (NoResultException e) {

        }
        return null;
    }

    @Override
    public List<Product> findByPrice(double min, double max, String category) {
        try (Session session = sessionFactory.openSession()) {

        } catch (NoResultException e) {

        }
        return null;
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
    public void findProduct(Product product) {

    }

    @Override
    public Product findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Product.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public void findProduct() {
        try (Session session = sessionFactory.openSession()) {
            List test = session.createQuery("select p from Mobile p ", Product.class).getResultList();
            System.out.println(test);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }
}
