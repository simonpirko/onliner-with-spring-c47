package by.fakeonliner.entity.shop;

import by.fakeonliner.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 255, min = 11, message = "Entered data has the wrong length")
    private String email;

    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 5, message = "Entered data has the wrong length")
    private String password;

    @NotBlank(message = "Empty field")
    @Length(max = 255, message = "Entered data has the wrong length")
    private String name;

    @NotBlank(message = "Empty field")
    @Length(max = 13, min = 13, message = "Entered data has the wrong length")
    private String phoneNumber;

    @NotBlank(message = "Empty field")
    @Length(max = 255, message = "Entered data has the wrong length")
    private String contactAddress;

    @Length(max = 255, message = "Entered data has the wrong length")
    private String description;

    private double amountOfMarks;

    private int numberOfMarks;

    @OneToMany
    private List<Product> products;

    public Shop(String email, String password, String name, String phoneNumber, String contactAddress, String description) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contactAddress = contactAddress;
        this.description = description;
    }
}
