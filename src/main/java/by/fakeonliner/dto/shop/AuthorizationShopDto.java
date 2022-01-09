package by.fakeonliner.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorizationShopDto {

    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 11, message = "Entered data has the wrong length")
    private String email;

    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 5, message = "Entered data has the wrong length(min 5)")
    private String password;

}
