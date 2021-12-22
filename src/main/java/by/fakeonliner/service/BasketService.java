package by.fakeonliner.service;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.BasketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketDao basketDao;

    public void addProduct(ProductDto product) {
        BasketProductDto basketProduct = new BasketProductDto();
        setFieldsBasket(product, basketProduct);
        basketProduct.setAmount(1);
        basketDao.addProduct(basketProduct);
    }

    @Transactional
    public void addProductDb(BasketProductDto product) {
        if (basketDao.existByProductDto(product)) {
            int amount = basketDao.getProductAmount(product);
            product.setAmount(++amount);
            basketDao.updateProduct(product);
        } else {
            basketDao.addProduct(product);
        }
    }

    public List<BasketProductDto> getProductList(User user) {
        return basketDao.getBasketProducts(user);
    }

    public List<BasketProductDto> getProductListFromDb(User user) {
        return basketDao.getBasketProducts(user);
    }

    @Transactional
    public void deleteProductFromBd(BasketProductDto product) {
        int amount = basketDao.getProductAmount(product);
        if (amount > 1) {
            basketDao.updateProduct(product);
        } else {
            basketDao.deleteProductDb(product);
        }
    }

    public void deleteProductFromMemory(BasketProductDto product) {
        basketDao.deleteProductDb(product);
    }

    private void setFieldsBasket(ProductDto product, BasketProductDto productDto) {
        productDto.getProduct().setId(product.getId());
        productDto.getProduct().setBrand(product.getBrand());
        productDto.getProduct().setModel(product.getModel());
        productDto.getProduct().setPrice(product.getPrice());
        productDto.getProduct().setDescription(product.getDescription());
        productDto.getProduct().setAverageRating(product.getAverageRating());
        productDto.getProduct().setMarketLaunchDate(product.getMarketLaunchDate());
        productDto.getProduct().setImage(product.getImage());
        productDto.setAmount(1);
    }
}
