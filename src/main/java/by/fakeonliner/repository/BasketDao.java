package by.fakeonliner.repository;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.entity.user.User;

import java.util.List;

public interface BasketDao {

    void addProduct(BasketProductDto product);
    void deleteProductDb(BasketProductDto product);

    List<BasketProductDto> getBasketProducts(User user);

    int getProductAmount(BasketProductDto product);

    void updateProduct(BasketProductDto product);

    boolean existByProductDto(BasketProductDto product);
}
