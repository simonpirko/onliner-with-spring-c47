package by.fakeonliner.service;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public ProductDto findById(int id) {
        return productDao.findById(id);
    }

    public boolean existByModel(String model) {
        return productDao.existByModel(model);
    }

    public List<ProductDto> findByModel(String model) {
        return productDao.findByModel(model);
    }

    public void delete(long id) {
        productDao.delete(id);
    }

    public List<ProductDto> findByBrand(String name, String category) {
        return productDao.findByBrand(name, category);
    }

    public List<ProductDto> findByPrice(double min, double max, String category) {
        return productDao.findByPrice(min, max, category);
    }

    public List<ProductDto> findByAllFromCategory(String category) {
        return productDao.findByAllFromCategory(category);
    }
}
