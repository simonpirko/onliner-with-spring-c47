package by.fakeonliner.service;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.entity.product.Laptop;
import by.fakeonliner.entity.product.Mobile;
import by.fakeonliner.repository.CategoryProductDao;
import by.fakeonliner.repository.jdbc.JdbcLaptopDao;
import by.fakeonliner.repository.jdbc.JdbcMobileDao;
import by.fakeonliner.repository.jdbc.JdbcProductDao;

import java.util.List;

public class ProductService {
    private final JdbcProductDao jdbcProductDao = new JdbcProductDao();
    private final CategoryProductDao jdbcMobileDao = new JdbcMobileDao();
    private final CategoryProductDao jdbcLaptopDao = new JdbcLaptopDao();
    private static ProductService instance;

    private ProductService(){};

    public static synchronized ProductService getInstance() {
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }

    public ProductDto findById(int id){
        return jdbcProductDao.findById(id);
    }

    public boolean existByModel(String model){
        return jdbcProductDao.existByModel(model);
    }

    public List<ProductDto> findByModel(String model){
        return jdbcProductDao.findByModel(model);
    }

    public void delete(long id){
        jdbcProductDao.delete(id);
    }

    public List<ProductDto> findByBrand(String name, String category){
        return jdbcProductDao.findByBrand(name, category);
    }

    public List<ProductDto> findByPrice(double min, double max, String category){
        return jdbcProductDao.findByPrice(min, max, category);
    }

    public List<ProductDto> findByAllFromCategory(String category){
        return jdbcProductDao.findByAllFromCategory(category);
    }

    public Object getProduct(long id, String category) {
        switch(category) {
            case "mobile" : {
                return findMobileById(id);
            }
            case "laptop" : {
                return findLaptopById(id);
            }
        }
        return null;
    }

    public List<ProductDto> getProductListHome() {
        return jdbcProductDao.getProductListHome();
    }

    private Mobile findMobileById(long id) {
        return (Mobile) jdbcMobileDao.getProductByIdAndType(id);
    }

    private Laptop findLaptopById(long id) {
        return (Laptop) jdbcLaptopDao.getProductByIdAndType(id);
    }
}
