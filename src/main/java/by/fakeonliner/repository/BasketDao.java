package by.fakeonliner.repository;

import by.fakeonliner.dto.BasketProductDto;

import java.util.List;

public interface BasketDao {

    void addProduct(BasketProductDto product);

    boolean addProductDb(long id, long productId, int amount);

    void deleteProduct(long productId, long userId);

    List<BasketProductDto> getBasket();

    List<BasketProductDto> getBasketFromDb(long userId);

    int getProductAmount(long productId, long userId);

    void changeProductAmount(long productId, long userId, int amount);
}
