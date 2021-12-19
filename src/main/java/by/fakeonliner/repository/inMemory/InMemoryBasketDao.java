package by.fakeonliner.repository.inMemory;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.BasketDao;

import java.util.LinkedList;
import java.util.List;

public class InMemoryBasketDao implements BasketDao {
    private static LinkedList<BasketProductDto> basket = new LinkedList<>();

    @Override
    public void addProduct(BasketProductDto product) {
        for (int i = 0; i < basket.size(); i++) {
            if (basket.get(i).getProduct().getModel().equals(product.getProduct().getModel())) {
                int amount = basket.get(i).getAmount() + 1;
                basket.get(i).setAmount(amount);
                return;
            }
        }
        basket.addFirst(product);
    }

    @Override
    public void deleteProductDb(BasketProductDto product) {
        for(BasketProductDto item : basket) {
            if (item.getId() == product.getProduct().getId()) {
                if (item.getAmount() == 1) {
                    basket.remove(item);
                    return;
                }
                int amount = item.getAmount() - 1;
                item.setAmount(amount);
            }
        }
    }

    @Override
    public List<BasketProductDto> getBasketProducts(User user) {
        return null;
    }

    @Override
    public int getProductAmount(BasketProductDto product) {
        return 0;
    }

    @Override
    public void updateProduct(BasketProductDto product) {

    }

    @Override
    public boolean existByProductDto(BasketProductDto product) {
        return false;
    }
}
