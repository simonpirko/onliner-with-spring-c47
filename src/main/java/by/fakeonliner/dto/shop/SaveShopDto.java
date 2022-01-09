package by.fakeonliner.dto.shop;

import by.fakeonliner.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaveShopDto {
    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 11, message = "Entered data has the wrong length")
    private String email;

    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 5, message = "Entered data has the wrong length(min = 5)")
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

    @NotBlank(message = "Empty field")
    @Length(max = 255, message = "Entered data has the wrong length")
    private String description;

    @Column(columnDefinition = "double default 0")
    private double amountOfMarks;

    @Column(columnDefinition = "int default 0")
    private int numberOfMarks;

    @OneToMany
    private List<Product> products;
}
