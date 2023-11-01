package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.CategoryDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
    List<Product> getProductsByCategoryName(String categoryName);
}
