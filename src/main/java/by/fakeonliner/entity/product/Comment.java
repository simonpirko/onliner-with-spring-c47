package by.fakeonliner.entity.product;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "comment_id")
    private long commentID;

    private String description;

    private Timestamp timestamp;

    private String comment;

    public Comment(String comment, Timestamp timestamp) {
        this.comment = comment;
        this.timestamp = timestamp;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comments_product"
            ,joinColumns = @JoinColumn(name = "comment_id")
            ,inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
}