package by.fakeonliner.entity.shop;

import by.fakeonliner.entity.product.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Shop {
    private long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String contactAddress;
    private String description;
    private String rating;
    private int countRating;

    private List<Comment> comments;

    public Shop(String password, String name, String phoneNumber, String email) {
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
