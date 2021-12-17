package by.fakeonliner.service;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.dto.ProductDto;
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
        BasketProductDto basketProductDto = new BasketProductDto();
        setFieldsBasket(product, basketProductDto);
        basketProductDto.setAmount(1);
        inMemoryBasketDao.addProduct(basketProductDto);
    }

    public void addProductDb(long id, long productId) {
        int amount = jdbcBasketDao.getProductAmount(productId, id);
        if (amount > 0) {
            jdbcBasketDao.changeProductAmount(productId, id, ++amount);
        } else {
            jdbcBasketDao.addProductDb(id, productId, ++amount);
        }
    }

    public List<BasketProductDto> getProductList() {
        return inMemoryBasketDao.getBasket();
    }

    public List<BasketProductDto> getProductListFromDb(long id) {
        return jdbcBasketDao.getBasketFromDb(id);
    }

    public void deleteProductFromBd(long productId, long userId) {
        int amount = jdbcBasketDao.getProductAmount(productId, userId);
        if (amount > 1) {
            jdbcBasketDao.changeProductAmount(productId, userId, --amount);
        } else {
            jdbcBasketDao.deleteProduct(productId, userId);
        }
    }

    public void deleteProductFromMemory(long productId) {
        inMemoryBasketDao.deleteProduct(productId, 1);
    }

    private void setFieldsBasket(ProductDto product, BasketProductDto basketProductDto) {
        basketProductDto.setId(product.getId());
        basketProductDto.setBrand(product.getBrand());
        basketProductDto.setModel(product.getModel());
        basketProductDto.setPrice(product.getPrice());
        basketProductDto.setDescription(product.getDescription());
        basketProductDto.setAverageRating(product.getAverageRating());
        basketProductDto.setMarketLaunchDate(product.getMarketLaunchDate());
        basketProductDto.setImage(product.getImage());
        basketProductDto.setAmount(1);
    }
}
