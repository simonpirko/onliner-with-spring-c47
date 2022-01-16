package by.fakeonliner.dao.inmemory;

import by.fakeonliner.dao.BasketDao;
import by.fakeonliner.dto.basket.ProductBasketDto;
import by.fakeonliner.entity.product.Product;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryBasketDao implements BasketDao {

    private static LinkedList<ProductBasketDto> products = new LinkedList<>();


    @Override
    public void save(ProductBasketDto productBasketDto) {
        productBasketDto.setCount(1);
        products.addFirst(productBasketDto);
    }

    @Override
    public List<ProductBasketDto> getProducts() {
        return products;
    }

    @Override
    public boolean existByModel(ProductBasketDto productBasketDto) {
        return products.stream().filter(prod -> prod.getProduct().getModel().equals(productBasketDto.getProduct().getModel()))
                .findFirst().isPresent();
    }

    @Override
    public long getProductCount(ProductBasketDto productBasketDto) {
        for (ProductBasketDto product : products) {
            if (product.getProduct().getModel().equals(productBasketDto.getProduct().getModel())) {
                return product.getCount();
            }
        }
        return  0;
    }

    @Override
    public ProductBasketDto getProductById(long id) {
        for (ProductBasketDto product : products) {
            if(product.getProduct().getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        LinkedList<ProductBasketDto> list = new LinkedList<>();
        for(ProductBasketDto product : products) {
            if(product.getProduct().getId() != id) {
                list.add(product);
            }
        }
        products = list;
    }

    @Override
    public void updateProduct(ProductBasketDto productBasketDto) {
        IntStream.range(0, products.size()).filter(i -> products.get(i).getProduct().getModel().equals(productBasketDto.getProduct().getModel())).forEach(i -> products.set(i, productBasketDto));
    }

    @Override
    public void deleteAllProducts() {
        products = new LinkedList<>();
    }

    public long getBasketSize() {
        return products.stream().mapToLong(ProductBasketDto::getCount).sum();
    }
}
