package by.fakeonliner.dao;

import by.fakeonliner.entity.product.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    void save(Product product);

    boolean existByModel(String model);

    List<Product> findByModel(String model);

    List<Product> findByCategoryId(long categoryId);

    List<Product> findByPrice(double min, double max, long categoryId);

    void edit(Product product);

    void delete(long id);

    Product findById(long id);

    List<Product> getAllProducts();
}
