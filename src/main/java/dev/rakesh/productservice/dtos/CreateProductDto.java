package dev.rakesh.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    private Long Id;
    private String title;
    private BigDecimal price;
    private String description;
    private String image;
    private String category;
    private double ratingRate;
    private Integer ratingCount;

}
