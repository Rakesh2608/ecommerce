package dev.rakesh.productservice.client.fakestoreproductapi;

import dev.rakesh.productservice.dtos.ProductRequestDto;
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
public class FakeStoreProductClient {
    private final RestTemplateBuilder restTemplateBuilder;

    public List<ProductRequestDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductRequestDto[]> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", ProductRequestDto[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    public ProductRequestDto getProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductRequestDto> response =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}"
                        , ProductRequestDto.class, productId);
        ProductRequestDto productRequestDto = response.getBody();
        return productRequestDto;
    }

    public ProductRequestDto createProduct(ProductRequestDto productRequestDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductRequestDto> responseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products", productRequestDto,
                ProductRequestDto.class);

        ProductRequestDto productDto = responseEntity.getBody();
        return productDto;
    }

    public ProductRequestDto updateProduct(Long productId, ProductRequestDto newProductRequestDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductRequestDto productRequestDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                newProductRequestDto, ProductRequestDto.class, productId);

        return productRequestDto;
    }
}
