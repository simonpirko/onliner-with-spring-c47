package by.fakeonliner.repository.inMemory;


import by.fakeonliner.entity.product.Laptop;
import by.fakeonliner.entity.product.Mobile;

import java.util.LinkedList;
import java.util.List;

public class InMemoryProductDao{

    private final static List<Laptop> laptops = new LinkedList<>();
    private final static List<Mobile> mobiles = new LinkedList<>();

//    @Override
//    public void save(Object object) {
//
//    }
//
//    @Override
//    public boolean existByModel(String model) {
//        return false;
//    }
//
//    @Override
//    public List<Object> findByModel(String name) {
//        return null;
//    }
//
//    @Override
//    public void edit(long id, Object object) {
//
//    }
//
//    @Override
//    public void delete(long id) {
//
//    }
//
//    @Override
//    public List<Object> findByBrand(String name, String category) {
//        return null;
//    }
//
//    @Override
//    public List<Object> findByAllFromCategory(String category) {
//        return null;
//    }
//
//    @Override
//    public List<Object> findByPrice(double a, double b, String category) {
//        return null;
//    }
}
