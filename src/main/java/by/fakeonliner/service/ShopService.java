package by.fakeonliner.service;

import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.repository.ShopDao;
import by.fakeonliner.repository.inMemory.InMemoryShopDao;

import java.util.List;

public class ShopService {
    private final ShopDao shopDao = new InMemoryShopDao();

    public boolean save(Shop shop) {

        if (shopDao.existByEmail(shop.getEmail())) {
          shopDao.save(shop);
          return true;
        } else {
          return false; 
        }
    }
  
    public Shop findByEmail(String email) {
        if (shopDao.existByEmail(email)) {
            return shopDao.getShopByEmail(email);
        }
        return null;
    }

    public List<Shop> getShopList() {
        return shopDao.getShopList();
    }
}
