package by.fakeonliner.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotBlank(message = "Empty field")
    private String email;

//    @NotBlank(message = "Empty field")
    private String password;

//    @NotBlank(message = "Empty field")
    private String firstName;

//    @NotBlank(message = "Empty field")
    private String lastName;

//    @NotBlank(message = "Empty field")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket;

    public void setRoleUser(RoleUser user) {
    }
}
