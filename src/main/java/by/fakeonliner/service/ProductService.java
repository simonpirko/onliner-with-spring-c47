package by.fakeonliner.service;

import by.fakeonliner.dao.ProductDao;
import by.fakeonliner.entity.product.Laptop;
import by.fakeonliner.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public void save(Product product){
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
        return productDao.getByCategoryId(categoryId);
    }

    public List<Product> findByPrice(double min, double max, String category) {
        return productDao.findByPrice(min, max, category);
    }

    public List<Product> findByAllFromCategory(String category) {
        return productDao.findByAllFromCategory(category);
    }

    public void findProduct(){
//        productDao.findProduct(ProductDao.getClass);

    }

}
