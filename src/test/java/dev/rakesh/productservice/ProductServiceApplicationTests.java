package dev.rakesh.productservice;

import dev.rakesh.productservice.model.Category;
import dev.rakesh.productservice.model.Product;
import dev.rakesh.productservice.model.Rating;
import dev.rakesh.productservice.repositories.CategoryRepository;
import dev.rakesh.productservice.repositories.ProductRepository;
import dev.rakesh.productservice.services.SelfProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest

class ProductServiceApplicationTests {

    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private SelfProductService selfProductService;

    @Test
    @Transactional
    @Commit
    void contextLoads() {
        Product product=new Product();
        Category category=new Category();
        category.setName("electronics");
        categoryRepository.save(category);
        product.setTitle("Phone");
        product.setPrice(BigDecimal.valueOf((1000)));
        product.setDescription("Good phone");
        product.setImageUrl("newImage.jpg");
        product.setCategory(category);
        Rating rating=new Rating();
        rating.setRate(4.5);
        product.setRating(rating);
        productRepository.save(product);
    }
   // @Test
//    void getAllProducts(){
//        List<Product> products=selfProductService.getAllProducts();
//        System.out.println(products);
//    }

    @Test
    public void whenModifyingPrimitives_thenOriginalValuesNotModified() {

        int x = 1;
        int y = 2;

        // Before Modification
        //assertEquals(x, 1);
        //assertEquals(y, 2);
        System.out.println(x+" "+y);
        modify(x, y);

        // After Modification
        //assertEquals(x, 1);
        //assertEquals(y, 2);
        System.out.println(x+" "+y);
    }

    public static void modify(int x1, int y1) {
        x1 = 5;
        y1 = 10;
    }
}
