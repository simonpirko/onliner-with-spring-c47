package by.fakeonliner.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
//@Entity
@Deprecated
public class Mobile{


    private String mobileType;

    private String processor;

    private String operationSystem;

    private double displaySize;

    private double frequency;

    private int ram;

    private String displayType;

}
