package dev.rakesh.productservice.dtos;

import dev.rakesh.productservice.model.Rating;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ProductRequestDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String image;
    private String category;
    private Rating rating;
}
