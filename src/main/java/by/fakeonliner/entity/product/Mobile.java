package by.fakeonliner.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Mobile extends Product {
    private String mobileType;
    private String processor;
    private String operationSystem;
    private double displaySize;
    private double frequency;
    private int ram;
    private String displayType;
}
