package by.fakeonliner.entity.product;

import by.fakeonliner.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentID;

    private String description;
    private LocalDateTime localDateTime;
    private String comment;

    @ManyToOne
    private User user;

    public Comment(String comment, LocalDateTime localDateTime) {
        this.comment = comment;
        this.localDateTime = localDateTime;
    }

    @ManyToMany
    @JoinTable(name = "comments_product"
            , joinColumns = @JoinColumn(name = "comment_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
}