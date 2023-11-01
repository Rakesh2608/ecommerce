package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.CreateProductDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.model.Rating;
import dev.rakesh.productservice.repositories.CategoryRepository;
import dev.rakesh.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //private final CategoryRepository categoryRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
       return productRepository.findById(productId);

    }

    @Override
    public Product createProduct(CreateProductDto createProductDto) {
        String categoryName=createProductDto.getCategory();
        Category category=categoryRepository.findByNameIgnoreCase(categoryName);
        if(category==null){
            category = new Category();
            category.setName(categoryName);
            categoryRepository.save(category);
        }
        Product product=new Product();
        product.setCategory(category);
        product.setTitle(createProductDto.getTitle());
        product.setDescription(createProductDto.getDescription());
        product.setImageUrl(createProductDto.getImage());
        product.setPrice(createProductDto.getPrice());
        Rating rating=new Rating();
        rating.setRate(createProductDto.getRatingRate());
        rating.setCount(createProductDto.getRatingCount());
        product.setRating(rating);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, CreateProductDto newProductRequestDto) {
        Optional<Product> product=productRepository.findById(productId);
        Product product1=product.orElse(new Product());
        product1.setTitle(newProductRequestDto.getTitle());
        product1.setPrice(newProductRequestDto.getPrice());
        product1.setImageUrl(newProductRequestDto.getImage());
        Category category=new Category();
        category.setName(newProductRequestDto.getCategory());
        product1.setCategory(category);
        productRepository.save(product1);
        return product1;

    }
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
