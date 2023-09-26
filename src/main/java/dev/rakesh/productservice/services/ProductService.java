package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.ProductRequestDto;

public interface ProductService {
    String getAllProducts();
    String getProductById(Long productId);
    String createProduct(ProductRequestDto productRequestDto);
    String updateProduct(Long productId,ProductRequestDto newProductRequestDto);
    String deleteProduct(Long productId);
}
