package by.fakeonliner.repository.inMemory;

import by.fakeonliner.dto.BasketProductDto;
import by.fakeonliner.repository.BasketDao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryBasketDao implements BasketDao {
    private static LinkedList<BasketProductDto> basket = new LinkedList<>();

    @Override
    public void addProduct(BasketProductDto product) {
        for (int i = 0; i < basket.size(); i++) {
            if (basket.get(i).getModel().equals(product.getModel())) {
                int amount = basket.get(i).getAmount() + 1;
                basket.get(i).setAmount(amount);
                return;
            }
        }
        basket.addFirst(product);
    }

    @Override
    public void deleteProduct(long productId, long userId) {
        List<BasketProductDto> list = new ArrayList<>();
        for(BasketProductDto item : basket) {
            if (item.getId() == productId) {
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
    public List<BasketProductDto> getBasket() {
        return basket;
    }

    @Override
    public List<BasketProductDto> getBasketFromDb(long userId) {
        return null;
    }

    @Override
    public int getProductAmount(long productId, long userId) {
        return 0;
    }

    @Override
    public void changeProductAmount(long productId, long userId, int amount) {

    }

    @Override
    public boolean addProductDb(long id, long productId, int amount) {
        return false;
    }
}
