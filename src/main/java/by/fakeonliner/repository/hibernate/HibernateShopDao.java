package by.fakeonliner.repository.hibernate;

import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.repository.ShopDao;
import by.fakeonliner.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateShopDao implements ShopDao {

    @Autowired
    private ShopService shopService;

    @Override
    public void save(Shop shop) {
        if(shopService.contains(shop.getEmail())){
            shopService.save(shop);
        }
    }

    @Override
    public Shop getShopByEmail(String email) {
        if(shopService.contains(email)){
            return shopService.getShop();
        } else {
            return null;
        }
    }

    @Override
    public List<Shop> getShopList() {
        return shopService.getAll();
    }


    @Override
    public void edit(Shop shop) {
        if(shopService.contains(shop.getEmail())){
            shopService.edit(shop);
        }
    }


    @Override
    public void delete(Shop shop) {
        if(shopService.contains(shop.getEmail())) {
            shopService.deleteByEmal(shop.getEmail());
        }
    }

    @Override
    public Shop getShop() {
        return shopService.getShop();
    }
}
