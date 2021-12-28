package by.fakeonliner.service;

import by.fakeonliner.dao.ProductDao;
import by.fakeonliner.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public void save(Product product) {
        productDao.save(product);
    }

    public Product findById(int id) {
        return productDao.findById(id);
    }

    public boolean existByModel(String model) {
        return productDao.existByModel(model);
    }

    public List<Product> findByModel(String model) {
        return productDao.findByModel(model);
    }

    public void delete(long id) {
        productDao.delete(id);
    }

    public List<Product> getByCategoryId(long categoryId) {
        return productDao.findByCategoryId(categoryId);
    }

    public List<Product> findByPrice(double min, double max, long categoryId) {
        return productDao.findByPrice(min, max, categoryId);
    }

    public List<Product> getAllFromCategory(long categoryId) {
        return productDao.findByCategoryId(categoryId);
    }

    public void findProduct() {
//        productDao.findProduct(ProductDao.getClass);

    }

}
