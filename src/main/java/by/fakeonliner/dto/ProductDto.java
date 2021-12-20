package by.fakeonliner.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class ProductDto {

    @Id
    private long id;

    private String brand;

    private double price;

    private String model;

    private int marketLaunchDate;

    private double averageRating;

    private String description;

    private String image;
}
