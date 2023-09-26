package dev.rakesh.productservice.controllers;

import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public String getAllProducts(){
        return "Getting All products";

    }

    @GetMapping("/{productId}")
    public String getProductById(@PathVariable("productId") Long productId){
        return "getting a single product";
    }

    @PostMapping
    public String createProduct(@RequestBody ProductRequestDto productRequestDto){
        return "Adding new  product";
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId,ProductRequestDto newProductRequestDto){
        return "updated a single product";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "deleted a single product";
    }



}
