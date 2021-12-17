package by.fakeonliner.repository.inMemory;

import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.repository.ShopDao;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShopDao implements ShopDao {

    private final static List<Shop> shops = new ArrayList<>();
    private long id = 0;

    @Override
    public void save(Shop shop) {
        shop.setId(id++);
        shops.add(shop);
    }

    @Override
    public boolean existByEmail(String email) {
        for (Shop sh : shops) {
            if (sh.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Shop getShopByEmail(String email) {
        for (Shop sh : shops) {
            if (sh.getEmail().equals(email)) {
                return sh;
            }
        }
        return null;
    }

    @Override
    public void edit(Shop shop) {
        for (Shop sh : shops) {
            if (sh.getId() == shop.getId()) {
                shops.remove(sh);
                shops.add(shop);
                return;
            }
        }
    }

    @Override
    public void delete(Shop shop) {
        for (Shop sh : shops) {
            if (sh.getId() == shop.getId()) {
                shops.remove(shop);
                return;
            }
        }
    }

    @Override
    public List<Shop> getShopList() {
        return null;
    }
}
