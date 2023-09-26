package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.ProductRequestDto;

public class FakeStoreProductServiceImpl implements ProductService {
    @Override
    public String getAllProducts() {
        return null;
    }

    @Override
    public String getProductById(Long productId) {
        return null;
    }

    @Override
    public String createProduct(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public String updateProduct(Long productId,ProductRequestDto newProductRequestDto) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
