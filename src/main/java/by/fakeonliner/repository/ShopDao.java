package by.fakeonliner.repository;

import by.fakeonliner.entity.shop.Shop;

import java.util.List;

public interface ShopDao {

    void save(Shop shop);
    boolean existByEmail(String email);
    Shop getShopByEmail(String email);
    List<Shop> getShopList();
    void edit(Shop shop);
    void delete(Shop shop);
    Shop findById(long id);
}
