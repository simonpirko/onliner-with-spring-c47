package by.fakeonliner.service;

import by.fakeonliner.dao.ShopDao;
import by.fakeonliner.entity.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopService {

    @Autowired
    private ShopDao shopDao;

    public boolean save(Shop shop) {
        if (shopDao.existByEmail(shop.getEmail())) {
            return false;
        } else {
            shopDao.save(shop);
            return true;
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
