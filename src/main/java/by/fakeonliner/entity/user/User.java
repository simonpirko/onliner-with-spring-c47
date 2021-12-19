package by.fakeonliner.entity.user;

import lombok.AllArgsConstructor;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Empty field")
    private String email;  //registration, authorization

    @NotBlank(message = "Empty field")
    private String password;

    @NotBlank(message = "Empty field")
    private String firstName;

    @NotBlank(message = "Empty field")
    private String lastName;

    @NotBlank(message = "Empty field")
    private String username;

    @NotBlank(message = "Empty field")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public User(long id) {
        this.id = id;
    }
}
