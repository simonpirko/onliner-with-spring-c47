package by.fakeonliner.dao;

import by.fakeonliner.dto.ProductBasketDto;

import java.util.List;

public interface BasketDao {
    void save (ProductBasketDto productBasketDto);
    List<ProductBasketDto> getProducts();
    void updateProduct(ProductBasketDto productBasketDto);
    boolean existByModel(ProductBasketDto productBasketDto);
    long getProductCount(ProductBasketDto productBasketDto);
    ProductBasketDto getProductById(long id);
    void deleteById(long id);
}
