package by.fakeonliner.entity.product;

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
    private long commentID;

    private String description;

    private LocalDateTime localDateTime;

    private String comment;

    public Comment(String comment, LocalDateTime localDateTime) {
        this.comment = comment;
        this.localDateTime = localDateTime;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comments_product"
            ,joinColumns = @JoinColumn(name = "comment_id")
            ,inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
}