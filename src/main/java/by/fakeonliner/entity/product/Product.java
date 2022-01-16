package by.fakeonliner.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Empty field")
    private String brand;

    private float price;

    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 3, message = "Entered data has the wrong length")
    private String model;

    private int marketLaunchDate;

    private double averageRating;

    private String description;

    @NotBlank(message = "Empty field")
    private String urlImage;

    private long categoryId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "product_descriptionfeaturevalue",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "descriptionfeaturevalues_id"))
    private List<DescriptionFeatureValue> descriptionFeatureValues;
}
