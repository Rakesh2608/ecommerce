package dev.rakesh.productservice.controllers;

import dev.rakesh.productservice.dtos.GetSingleProductResponseDto;
import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.services.FakeStoreProductServiceImpl;
import dev.rakesh.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
//   public ProductController(ProductService productService){
//       this.productService=productService;
//   }

    @GetMapping
    public List<Product> getAllProducts() {
        return  productService.getAllProducts();

    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetSingleProductResponseDto> getProductById(@PathVariable("productId") Long productId) {
        //GetSingleProductResponseDto getSingleProductResponseDto=new GetSingleProductResponseDto();
        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        headers.add(
                "auth-token","no-access"
        );

        ResponseEntity<GetSingleProductResponseDto> responseEntity=new
                ResponseEntity(productService.getProductById(productId),headers,HttpStatus.OK);


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
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ResponseEntity<Product> productResponseEntity=new
                ResponseEntity<>(productService.createProduct(productRequestDto),HttpStatus.OK);

        return productResponseEntity;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, ProductRequestDto newProductRequestDto) {
        return "updated a single product";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "deleted a single product";
    }


}
