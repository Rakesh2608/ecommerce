package dev.rakesh.productservice.services;

import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public class SelfCategoryService implements CategoryService{
    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return null;
    }
}
