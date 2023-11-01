package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.CategoryDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.repositories.CategoryRepository;
import dev.rakesh.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SelfCategoryService implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {

        return productRepository.findByCategory_Name(categoryName);
    }
}
