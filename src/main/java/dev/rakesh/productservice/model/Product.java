package dev.rakesh.productservice.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private BigDecimal price;
    private Category category;
    private String imageUrl;
    private Rating rating;
}
