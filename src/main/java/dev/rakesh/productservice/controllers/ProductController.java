package dev.rakesh.productservice.controllers;

import dev.rakesh.productservice.client.AuthClient.AuthenticationClient;
import dev.rakesh.productservice.client.AuthClient.dtos.ValidateResponseDto;
import dev.rakesh.productservice.client.AuthClient.models.Role;
import dev.rakesh.productservice.client.AuthClient.models.SessionStatus;
import dev.rakesh.productservice.dtos.CreateProductDto;
import dev.rakesh.productservice.dtos.GetSingleProductResponseDto;
import dev.rakesh.productservice.dtos.ProductResponseDto;
import dev.rakesh.productservice.exceptions.NotFoundException;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.model.Rating;
import dev.rakesh.productservice.services.FakeStoreProductServiceImpl;
import dev.rakesh.productservice.services.ProductService;
import dev.rakesh.productservice.services.SelfProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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

    //private final FakeStoreProductServiceImpl productService;
    private final ProductService productService;
    //private final SelfProductService productService;
    private final AuthenticationClient authenticationClient;
//   public ProductController(ProductService productService){
//       this.productService=productService;
//   }

    //Fetching all products.
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN")String token ,@Nullable @RequestHeader("USER_ID") Long userId ) {
//        if(token==null||userId==null){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        //Checking if token is valid...
//        ValidateResponseDto validateResponseDto= authenticationClient.validate(token,userId);
//        if(validateResponseDto.getSessionStatus().equals(SessionStatus.INVALID)){
//            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        //Check if user role is admin or not
//        boolean isAdmin=false;
//        for(Role role:validateResponseDto.getUserDto().getRoles()){
//            if (role.getName().equals("admin")) {
//                isAdmin = true;
//                break;
//            }
//        }
//        if(!isAdmin){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        List<Product> products=productService.getAllProducts();
        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();
        for(Product product:products){
            productResponseDtoList.add(ConvertToResponseDto(product));
        }
        return new ResponseEntity<>(productResponseDtoList,HttpStatus.OK);
    }

    private ProductResponseDto ConvertToResponseDto(Product product) {
        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImage(product.getImageUrl());
        productResponseDto.setPrice(product.getPrice());

        productResponseDto.setCategory(product.getCategory().getName());
        Rating rating=new Rating();
        rating.setRate(product.getRating().getRate());
        rating.setCount(product.getRating().getCount());
        productResponseDto.setRating(rating);

        return  productResponseDto;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetSingleProductResponseDto> getProductById(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(
                "auth-token", "no-access"
        );

        //Optional<Product> product=productService.getProductById(productId);
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
                ResponseEntity<>(
                        productService.createProduct(newProductRequestDto), HttpStatus.OK);
        return productResponseEntity;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody CreateProductDto productRequestDto) throws NotFoundException {

//      Optional<Product> product=selfProductService.getProductById(productId);
//        if(product.isEmpty()){
//            throw new NotFoundException("No product found with productId:"+productId);
//        }

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                productService.updateProduct(productId, productRequestDto),
                HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
         Optional<Product> product=productService.getProductById(productId);
         if(product.isEmpty()){
             throw new NotFoundException("No product found with product id: "+productId);
         }
        productService.deleteProduct(productId);
         return "Product deleted successfully!!!!";

//         ResponseEntity<ProductRequestDto> productResponseEntity=new ResponseEntity<>(selfProductService.deleteProduct(productId),HttpStatus.OK);
//         return  productResponseEntity;
    }

}
