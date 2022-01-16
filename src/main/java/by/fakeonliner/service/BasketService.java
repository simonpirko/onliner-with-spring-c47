package by.fakeonliner.service;

import by.fakeonliner.dao.inmemory.InMemoryBasketDao;
import by.fakeonliner.dto.basket.ProductBasketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private InMemoryBasketDao inMemoryBasketDao;

    public void save(ProductBasketDto productBasketDto) {
        if (inMemoryBasketDao.existByModel(productBasketDto)) {
            long count = inMemoryBasketDao.getProductCount(productBasketDto);
            productBasketDto.setCount(++count);
            inMemoryBasketDao.updateProduct(productBasketDto);
        } else {
            inMemoryBasketDao.save(productBasketDto);
        }
    }

    public List<ProductBasketDto> getProducts() {
        return inMemoryBasketDao.getProducts();
    }

    public ProductBasketDto getProductById(long id) {
        return inMemoryBasketDao.getProductById(id);
    }

    public void changeProductCount(ProductBasketDto product, long count) {
        product.setCount(count);
        inMemoryBasketDao.updateProduct(product);
    }

    public void deleteProduct(long id) {
        inMemoryBasketDao.deleteById(id);
    }

    public void deleteAllProducts() {
        inMemoryBasketDao.deleteAllProducts();
    }

    public long getBasketSize() {
        return inMemoryBasketDao.getBasketSize();
    }
}
