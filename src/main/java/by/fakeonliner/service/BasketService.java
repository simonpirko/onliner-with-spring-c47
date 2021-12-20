package by.fakeonliner.service;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.BasketDao;
import by.fakeonliner.repository.inMemory.InMemoryBasketDao;
import by.fakeonliner.repository.jdbc.JdbcBasketDao;

import java.util.List;

public class BasketService {
    private final BasketDao jdbcBasketDao = new JdbcBasketDao();
    private final BasketDao inMemoryBasketDao = new InMemoryBasketDao();
    private static BasketService instance;

    private BasketService(){};

    public static synchronized BasketService getInstance() {
        if(instance == null){
            instance = new BasketService();
        }
        return instance;
    }

    public void addProduct(ProductDto product) {
        BasketProductDto basketProduct = new BasketProductDto();
        setFieldsBasket(product, basketProduct);
        basketProduct.setAmount(1);
        inMemoryBasketDao.addProduct(basketProduct);
    }

    public void addProductDb(BasketProductDto product) {
        if(jdbcBasketDao.existByProductDto(product)) {
            int amount = jdbcBasketDao.getProductAmount(product);
            product.setAmount(++amount);
            jdbcBasketDao.updateProduct(product);
        } else {
            jdbcBasketDao.addProduct(product);
        }
    }

    public List<BasketProductDto> getProductList(User user) {
        return inMemoryBasketDao.getBasketProducts(user);
    }

    public List<BasketProductDto> getProductListFromDb(User user) {
        return jdbcBasketDao.getBasketProducts(user);
    }

    public void deleteProductFromBd(BasketProductDto product) {
        int amount = jdbcBasketDao.getProductAmount(product);
        if (amount > 1) {
            jdbcBasketDao.updateProduct(product);
        } else {
            jdbcBasketDao.deleteProductDb(product);
        }
    }

    public void deleteProductFromMemory(BasketProductDto product) {
        inMemoryBasketDao.deleteProductDb(product);
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
