package dev.rakesh.productservice.dtos;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ProductRequestDto {
    private String title;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private String category;
}
