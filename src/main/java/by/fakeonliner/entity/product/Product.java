package by.fakeonliner.entity.product;

import by.fakeonliner.entity.shop.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String brand;


    private BigDecimal price;

    private String model;

    private int marketLaunchDate;

    private double averageRating;

    private String description;

    private CategoryProduct categoryProduct;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comments_products"
            , joinColumns = @JoinColumn(name = "product_id")
            , inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "shop_products"
            , joinColumns = @JoinColumn(name = "product_id")
            , inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Shop> shops;

}