package dev.rakesh.productservice.controllers;

import dev.rakesh.productservice.dtos.CategoryDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.services.SelfCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final SelfCategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories(){
        //CategoryDto categoryDto=new CategoryDto();
        List<CategoryDto> categoriesDtoList=new ArrayList<>();
        List<Category> categories=categoryService.getAllCategories();
        for(Category c:categories){
            categoriesDtoList.add(ConvertToDto(c));
        }
        return categoriesDtoList;
    }

    private CategoryDto ConvertToDto(Category c) {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName(c.getName());
        categoryDto.setDescription(c.getDescription());
        return categoryDto;
    }

    @GetMapping("/{categoryName}")
    public List<Product> getProductsByCategoryName(@PathVariable("categoryName") String categoryName){
        return categoryService.getProductsByCategoryName(categoryName);
    }

}
