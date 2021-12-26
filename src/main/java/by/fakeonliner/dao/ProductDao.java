package by.fakeonliner.dao;

import by.fakeonliner.entity.product.Laptop;
import by.fakeonliner.entity.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    void save(Product product);

    boolean existByModel(String model);

    List<Product> findByModel(String model);

    List<Laptop> findByBrand(String name, String category);

    List<Product> findByAllFromCategory(String category);

    List<Product> findByPrice(double min, double max, String category);

    void edit(Product product);

    void delete(long id);

    Product findById(int id);
}
