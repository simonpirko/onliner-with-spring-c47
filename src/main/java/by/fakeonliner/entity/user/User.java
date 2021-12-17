package by.fakeonliner.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private long id;
    private String email;  //registration, authorization
    private String password;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private RoleUser roleUser;
}
