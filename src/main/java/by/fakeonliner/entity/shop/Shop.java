package by.fakeonliner.entity.shop;

import by.fakeonliner.entity.product.Comment;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
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

    private String rating;

    private int countRating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Shop(String password, String name, String phoneNumber, String email) {
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
