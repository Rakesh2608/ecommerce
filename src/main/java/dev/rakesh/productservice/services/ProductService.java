package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.GetSingleProductResponseDto;
import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.dtos.ProductResponseDto;
import dev.rakesh.productservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    Optional<Product> getProductById(Long productId);
    Product createProduct(ProductRequestDto productRequestDto);
    Product updateProduct(Long productId, ProductRequestDto newProductRequestDto);
    boolean deleteProduct(Long productId);
}
