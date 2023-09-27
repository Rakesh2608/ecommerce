package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(ProductRequestDto productRequestDto);
    Product updateProduct(Long productId,ProductRequestDto newProductRequestDto);
    boolean deleteProduct(Long productId);
}
