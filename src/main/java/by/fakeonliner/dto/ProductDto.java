package by.fakeonliner.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDto {
    private long id;
    private String brand;
    private double price;
    private String model;
    private int marketLaunchDate;
    private double averageRating;
    private String description;
    private String image;
}
