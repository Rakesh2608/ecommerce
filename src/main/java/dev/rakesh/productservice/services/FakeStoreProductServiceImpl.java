package dev.rakesh.productservice.services;

import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.dtos.ProductResponseDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.model.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FakeStoreProductServiceImpl implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    //    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
//        this.restTemplateBuilder=restTemplateBuilder;
//    }

    //Generic RequestEntity
    public <T> ResponseEntity<T> requestForEntity(String url,HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate=restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductResponseDto[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductResponseDto[].class);

        //        for(ProductRequestDto productRequestDto:responseEntity.getBody()){
//
//            Product productDto=ConvertProductFromDto(productRequestDto);
//            answer.add(productDto);
//        }
        //ProductResponseDto productResponseDto = getProductResponseDto(product);
        List<ProductResponseDto> answer = new ArrayList<>(Arrays.asList(responseEntity.getBody()));

        return answer;

    }

    private ProductResponseDto getProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setCategory(product.getCategory().getName());
        productResponseDto.setImage(product.getImageUrl());
        Rating rating = new Rating();
        rating.setRate(product.getRating().getRate());
        rating.setCount(product.getRating().getCount());
        productResponseDto.setRating(rating);
        return productResponseDto;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductRequestDto> response =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}"
                        , ProductRequestDto.class, productId);
        ProductRequestDto productRequestDto = response.getBody();

        if(productRequestDto==null){
            return Optional.empty();
        }
        return Optional.of(ConvertProductFromDto(productRequestDto));

    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductRequestDto> responseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products", productRequestDto,
                ProductRequestDto.class);

        ProductRequestDto productDto = responseEntity.getBody();

        assert productDto != null;
        return ConvertProductFromDto(productDto);

    }

    private Product ConvertProductFromDto(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setId(productRequestDto.getId());
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl(productRequestDto.getImage());
        Category category = new Category();
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
        RestTemplate restTemplate = restTemplateBuilder.build();
        //ProductRequestDto response = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", newProductRequestDto, ProductRequestDto.class, productId);

//        ResponseEntity<ProductRequestDto> responseEntity=requestForEntity(
//                "https://fakestoreapi.com/products/{id}",HttpMethod.PATCH,
//                newProductRequestDto, ProductRequestDto.class,productId
//                );

        ProductRequestDto productRequestDto =restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                newProductRequestDto,ProductRequestDto.class,productId);

        return ConvertProductFromDto(productRequestDto);
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
