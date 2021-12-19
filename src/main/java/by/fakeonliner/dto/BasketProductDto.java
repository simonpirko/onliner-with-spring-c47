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
@Table(name = "basket")
public class BasketProductDto {

    @Id
    private long id;

    @OneToOne
    @Column(name = "user_id")
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
