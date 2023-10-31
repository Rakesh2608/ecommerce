package dev.rakesh.productservice.repositories;

import dev.rakesh.productservice.dtos.CreateProductDto;
import dev.rakesh.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {
    //Product save (CreateProductDto createProductDto);

}
