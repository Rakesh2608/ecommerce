package dev.rakesh.productservice.dtos;

import dev.rakesh.productservice.model.Rating;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String image;
    private String category;
    private Rating rating;
}
