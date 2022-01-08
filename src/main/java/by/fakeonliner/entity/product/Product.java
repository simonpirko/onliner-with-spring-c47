package by.fakeonliner.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String brand;

    private float price;

    private String model;

    private int marketLaunchDate;

    private double averageRating;

    private String description;

    private String urlImage;

    private long categoryId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_descriptionfeaturevalue",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "descriptionfeaturevalues_id"))
    private List<DescriptionFeatureValue> descriptionFeatureValues;
}
