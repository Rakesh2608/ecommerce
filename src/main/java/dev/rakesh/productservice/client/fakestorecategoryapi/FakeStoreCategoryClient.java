package dev.rakesh.productservice.client.fakestorecategoryapi;

import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FakeStoreCategoryClient {
    private final RestTemplateBuilder restTemplateBuilder;

    public List<Category> getAllCategory(){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<Category[]> responseEntity=restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories",Category[].class
        );
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    public List<ProductRequestDto> getProductsByCategoryName(String categoryName){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDto[]> responseEntity=restTemplate.getForEntity(
                "https://fakestoreapi.com/products/category/{categoryName}",
                ProductRequestDto[].class,categoryName
        );
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }
}
