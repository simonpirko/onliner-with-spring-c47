package by.fakeonliner.dto;

import by.fakeonliner.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class BasketProductDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @OneToOne
    private ProductDto product;

    @Column(columnDefinition = "1")
    private int amount;


    public BasketProductDto(User user, ProductDto product) {
        this.user = user;
        this.product = product;
    }
}
