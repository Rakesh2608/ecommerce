package dev.rakesh.productservice.controllers;

import dev.rakesh.productservice.dtos.CreateProductDto;
import dev.rakesh.productservice.dtos.GetSingleProductResponseDto;
import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.dtos.ProductResponseDto;
import dev.rakesh.productservice.exceptions.NotFoundException;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
//   public ProductController(ProductService productService){
//       this.productService=productService;
//   }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products=productService.getAllProducts();
        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();
        for(Product product:products){
            productResponseDtoList.add(ConvertToResponseDto(product));
        }
        return productResponseDtoList;
    }

    private ProductResponseDto ConvertToResponseDto(Product product) {
        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImage(product.getImageUrl());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory().getName());
        productResponseDto.setRating(product.getRating());

        return  productResponseDto;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetSingleProductResponseDto> getProductById(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(
                "auth-token", "no-access"
        );

        Optional<Product> product=productService.getProductById(productId);
        if(product.isEmpty()){
            throw new NotFoundException("No product found with productId:"+productId);
        }
        ResponseEntity<GetSingleProductResponseDto> responseEntity = new
                ResponseEntity(product,
                headers, HttpStatus.OK);
        return responseEntity;

    }

///for reference.
//    @GetMapping("/restCountry")
//    public ResponseEntity<?> getCountrys() {
//        try {
//            String uri="https://fakestoreapi.com/products/1";
//            RestTemplate restTemplate = new RestTemplate();
//            String result = restTemplate.getForObject(uri, String.class);
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductDto newProductRequestDto) {
        ResponseEntity<Product> productResponseEntity = new
                ResponseEntity<>(productService.createProduct(newProductRequestDto), HttpStatus.OK);

        return productResponseEntity;
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody CreateProductDto productRequestDto) throws NotFoundException {

        Optional<Product> product=productService.getProductById(productId);
        if(product.isEmpty()){
            throw new NotFoundException("No product found with productId:"+productId);
        }

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                productService.updateProduct(productId, productRequestDto),
                HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductRequestDto> deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
         Optional<Product> product=productService.getProductById(productId);
         if(product.isEmpty()){
             throw new NotFoundException("No product found with product id: "+productId);

         }
         ResponseEntity<ProductRequestDto> productResponseEntity=new ResponseEntity<>(productService.deleteProduct(productId),HttpStatus.OK);
         return  productResponseEntity;
    }


}
