package by.fakeonliner.repository;

import by.fakeonliner.dto.ProductDto;

import java.util.List;

public interface ProductDao {

    void save(Object object);

    boolean existByModel(String model);

    List<ProductDto> findByModel(String model);

    List<ProductDto> findByBrand(String name, String category);

    List<ProductDto> findByAllFromCategory(String category);

    List<ProductDto> findByPrice(double min, double max, String category);

    void edit(ProductDto productDto);

    void delete(long id);

    ProductDto findById(int id);

}
