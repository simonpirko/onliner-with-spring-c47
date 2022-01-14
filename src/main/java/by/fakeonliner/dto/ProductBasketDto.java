package by.fakeonliner.dto;

import by.fakeonliner.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductBasketDto {

    private Product product;
    private long count;

    public ProductBasketDto(Product product) {
        this.product = product;
    }
}
