package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.CreateProductDto;
import dev.rakesh.productservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long productId);
    Product createProduct(CreateProductDto createProductDto);
    Product updateProduct(Long productId, CreateProductDto newProductRequestDto);
    void deleteProduct(Long productId);
}
