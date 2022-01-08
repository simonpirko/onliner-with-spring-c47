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
public class UpdateShopDto {
    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 11, message = "Entered data has the wrong length")
    private String email;

    @NotBlank(message = "Empty field")
    @Length(max = 255, min = 5, message = "Entered data has the wrong length")
    private String password;

    @NotBlank(message = "Empty field")
    @Length(max = 255, message = "Entered data has the wrong length")
    private String name;

    @NotBlank(message = "Empty field")
    @Length(max = 13, min = 13, message = "Entered data has the wrong length")
    private String phoneNumber;

    @NotBlank(message = "Empty field")
    @Length(max = 255, message = "Entered data has the wrong length")
    private String contactAddress;

    @Length(max = 255, message = "Entered data has the wrong length")
    private String description;
}
