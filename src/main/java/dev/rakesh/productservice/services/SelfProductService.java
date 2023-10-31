package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.CreateProductDto;
import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {

        return Optional.of(productRepository.getById(productId));
    }

    @Override
    public Product createProduct(CreateProductDto createProductDto) {
        Product product=new Product();
        product.setTitle(createProductDto.getTitle());
        Category category=new Category();
        category.setName(createProductDto.getCategory());
        product.setCategory(category);
        product.setDescription(createProductDto.getDescription());
        product.setImageUrl(createProductDto.getImage());
        product.setPrice(createProductDto.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, CreateProductDto newProductRequestDto) {
        return null;
    }

    @Override
    public ProductRequestDto deleteProduct(Long productId) {

        return null;
    }
}
