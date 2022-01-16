package by.fakeonliner.dto.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {

    @NotBlank(message = "Field is empty")
    @NotEmpty
    private String country;

    @NotBlank(message = "Field is empty")
    @NotEmpty
    private String address;

    @NotEmpty
    @NotBlank(message = "Field is empty")
    private String firstName;

    @NotEmpty
    @NotBlank(message = "Field is empty")
    private String lastName;

    @NotEmpty
    @NotBlank(message = "Field is empty")
    @Email(message = "Email entered incorrectly")
    private String email;

    @NotEmpty
    @NotBlank(message = "Field is empty")
    private String phoneNumber;

    @NotEmpty
    @NotBlank(message = "Field is empty")
    private String cardOwnerName;

    @NotNull(message = "Field is empty")
    @Min(value = 1111111111, message = "Wrong length")
    @Positive(message = "Value is not positive")
    private long cardNumber;

    @NotNull(message = "Field is empty")
    @Min(value = 1, message = "Wrong length")
    @Max(value = 12, message = "Wrong length")
    @Positive(message = "Value is not positive")
    private int month;

    @NotNull(message = "Field is empty")
    @Min(value = 1, message = "Wrong length")
    @Max(value = 99, message = "Wrong length")
    @Positive(message = "Value is not positive")
    private int year;

    @NotNull(message = "Field is empty")
    @Min(value = 111, message = "Wrong length")
    @Positive(message = "Value is not positive")
    private int cvv;

}
