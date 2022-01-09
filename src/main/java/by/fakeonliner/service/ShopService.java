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

    public void save(Shop shop) {
        shopDao.save(shop);
    }

    public void edit(Shop shop) {
        shopDao.edit(shop);
    }

    public Shop findById(long id) {
        return shopDao.findById(id);
    }

    public Shop getShopByEmail(String email) {
        return shopDao.getShopByEmail(email);
    }

    public boolean existByEmail(String email) {
        return shopDao.existByEmail(email);
    }

    public List<Shop> getShopList() {
        return shopDao.getShopList();
    }
}
