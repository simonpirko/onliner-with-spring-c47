package by.fakeonliner.entity.product;

import by.fakeonliner.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Comment {
    private long commentID = 0;
    private long productID;
    private User user;
    private String description;
    private Timestamp timestamp;
    private String comment;


    public Comment(User user, long productID, String comment, Timestamp timestamp) {
        this.user = user;
        this.productID = productID;
        this.comment = comment;
        this.timestamp = timestamp;
        ++commentID;
    }
}