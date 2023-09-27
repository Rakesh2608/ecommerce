package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.model.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FakeStoreProductServiceImpl implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;

//    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
//        this.restTemplateBuilder=restTemplateBuilder;
//    }
    @Override
    public List<Product> getAllProducts() {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDto[]> responseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products",ProductRequestDto[].class);

        List<Product> answer =new ArrayList<>();

        for(ProductRequestDto productRequestDto:responseEntity.getBody()){

            Product productDto=ConvertProductFromDto(productRequestDto);
            answer.add(productDto);
        }

        return  answer;

    }

    @Override
    public Product getProductById(Long productId) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        ResponseEntity<ProductRequestDto> response= restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",ProductRequestDto.class,productId);
        ProductRequestDto productRequestDto=response.getBody();

        assert productRequestDto != null;
        return ConvertProductFromDto(productRequestDto);

    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDto> responseEntity= restTemplate.postForEntity("https://fakestoreapi.com/products",productRequestDto,ProductRequestDto.class);

        ProductRequestDto productDto=responseEntity.getBody();

        assert productDto != null;
        return ConvertProductFromDto(productDto);

    }

    private Product ConvertProductFromDto(ProductRequestDto productRequestDto) {
        Product product=new Product();
        product.setId(productRequestDto.getId());
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl(productRequestDto.getImage());
        Category category=new Category();
        category.setName(productRequestDto.getCategory());
        product.setCategory(category);
//        Rating rating=new Rating();
//        rating.setRate(productRequestDto.getRating().getRate());
//        rating.setCount(productRequestDto.getRating().getCount());
//        product.setRating(rating);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, ProductRequestDto newProductRequestDto) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
