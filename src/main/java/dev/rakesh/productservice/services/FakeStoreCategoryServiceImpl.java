package dev.rakesh.productservice.services;

import dev.rakesh.productservice.client.fakestorecategoryapi.FakeStoreCategoryClient;
import dev.rakesh.productservice.dtos.CategoryDto;
import dev.rakesh.productservice.dtos.ProductRequestDto;
import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.model.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeStoreCategoryServiceImpl implements CategoryService{

    private final FakeStoreCategoryClient fakeStoreCategoryClient;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return fakeStoreCategoryClient.getAllCategory();
    }
    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        List<ProductRequestDto> productRequestDtoList=fakeStoreCategoryClient.getProductsByCategoryName(categoryName);

        List<Product> products=new ArrayList<>();
        for(ProductRequestDto productRequestDto:productRequestDtoList){
            products.add(ConvertProductFromDto(productRequestDto));
        }
        return products;
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
        Rating rating = new Rating();
        rating.setRate(productRequestDto.getRating().getRate());
        rating.setCount(productRequestDto.getRating().getCount());
        product.setRating(rating);
        return product;
    }

}
