package by.fakeonliner.entity.shop;

import by.fakeonliner.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Empty field")
    private String email;

    @NotBlank(message = "Empty field")
    private String password;

    @NotBlank(message = "Empty field")
    private String name;

    @NotBlank(message = "Empty field")
    private String phoneNumber;

    @NotBlank(message = "Empty field")
    private String contactAddress;

    private String description;

    private double amountOfMarks;

    private int numberOfMarks;

    @OneToMany
    private List<Product> products;
}
