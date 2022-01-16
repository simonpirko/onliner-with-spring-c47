package by.fakeonliner.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_descriptionfeaturevalue")
public class DescriptionFeatureValueDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_id")
    private long product_id;

    @Column(name = "descriptionfeaturevalues_id")
    private long descriptionfeaturevalues_id;
}
