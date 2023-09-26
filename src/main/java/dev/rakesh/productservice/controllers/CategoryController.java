package dev.rakesh.productservice.controllers;

import dev.rakesh.productservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/categories")
@RequiredArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public String getAllCategories(){
        return "getting all categories";
    }

    @GetMapping("/{productId}")
    public String getCategoryById(@PathVariable("productId") Long productId){
        return "getting a single product";
    }

}
