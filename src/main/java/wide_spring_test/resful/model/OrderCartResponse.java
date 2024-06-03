package wide_spring_test.resful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCartResponse {

    private Long id;

    private Integer quantity;

    private Double sub_total;

    private ProductResponse product;
}
