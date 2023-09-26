package dev.rakesh.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String title;
    private String description;
    private BigDecimal price;
    private Category category;
    private String imageUrl;
}
