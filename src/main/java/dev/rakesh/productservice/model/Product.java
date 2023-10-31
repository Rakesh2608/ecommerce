package dev.rakesh.productservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private BigDecimal price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    private String imageUrl;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Rating rating;
}
