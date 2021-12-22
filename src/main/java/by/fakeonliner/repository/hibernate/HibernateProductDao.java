package by.fakeonliner.repository.hibernate;


import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.repository.ProductDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HibernateProductDao implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Object object) {
        Session session = sessionFactory.openSession();
        session.save(object);
        session.close();
    }

    @Override
    public boolean existByModel(String model) {
        Session session = sessionFactory.openSession();
        boolean exist = session.createQuery("from Product where model = :mo")
                .setParameter("mo", model).uniqueResult() != null;
        session.close();
        return exist;
    }

    @Override
    public List<ProductDto> findByModel(String model) {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Product where model = :pm", Product.class)
                .setParameter("pm", model)
                .getResultList();
        session.close();
        return list;
    }

    @Override
    public List<ProductDto> findByBrand(String name, String category) {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Product where model = :mo and categoryProduct = :cat")
                .setParameter("mo", name)
                .setParameter("cat", category)
                .getResultList();
        session.close();
        return list;
    }

    @Override
    public List<ProductDto> findByAllFromCategory(String category) {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Product where categoryProduct = :pc", Product.class)
                .setParameter("pc", category)
                .getResultList();
        session.close();
        return list;
    }

    @Override
    public List<ProductDto> findByPrice(double min, double max, String category) {
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Product where price < :min and price > :max and categoryProduct = :cat")
                .setParameter("min", min)
                .setParameter("max", max)
                .setParameter("cat", category)
                .getResultList();
        session.close();
        return list;
    }

    @Override
    public void edit(ProductDto productDto) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(productDto);
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Product product = session.find(Product.class, id);
        session.delete(product);
        session.close();
    }

    @Override
    public ProductDto findById(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where id = :im", Product.class);
        query.setParameter("im", id);
        ProductDto productdto = (ProductDto) query.getSingleResult();
        session.close();
        return productdto;
    }
}
