package dev.rakesh.productservice.client.fakestoreproductapi;

import dev.rakesh.productservice.dtos.CreateProductDto;
import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FakeStoreProductClient {
    private final RestTemplateBuilder restTemplateBuilder;
    private final ProductRepository productRepository;

    //Generic RequestEntity
    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

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

    public CreateProductDto createProduct(CreateProductDto createProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<CreateProductDto> responseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products", createProductDto,
                CreateProductDto.class);

        CreateProductDto productDto = responseEntity.getBody();

        //CreateProductDto productDto=productRepository.save(createProductDto);
        return productDto;
    }

    public CreateProductDto updateProduct(Long productId, CreateProductDto newProductRequestDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        CreateProductDto productDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                newProductRequestDto, CreateProductDto.class, productId);

        return productDto;
    }

    public ProductRequestDto deleteProduct(Long productId){
        ResponseEntity<ProductRequestDto> productResponseEntity= requestForEntity("https://fakestoreapi.com/products/{id}",HttpMethod.DELETE, null,ProductRequestDto.class,productId);
        return productResponseEntity.getBody();
    }
}
