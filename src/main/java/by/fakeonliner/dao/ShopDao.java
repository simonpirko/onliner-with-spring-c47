package by.fakeonliner.dao;

import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.shop.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDao {

    void save(Shop shop);
    boolean existByEmail(String email);
    Shop getShopByEmail(String email);
    List<Shop> getShopList();
    void edit(Shop shop);
    void delete(Shop shop);
    Shop findById(long id);
 }
