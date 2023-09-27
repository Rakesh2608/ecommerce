package dev.rakesh.productservice.dtos;

import dev.rakesh.productservice.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}
