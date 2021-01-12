package by.minsk.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Product {
    private Long id;
    private String title;
    private ProductType productType;
    private Brand brand;
    private BigDecimal price;
}
