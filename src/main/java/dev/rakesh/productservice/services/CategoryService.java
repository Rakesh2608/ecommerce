package dev.rakesh.productservice.services;

import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Product> getProductsByCategoryName(String categoryName);
}
