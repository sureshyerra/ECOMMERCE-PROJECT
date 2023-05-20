package com.example.Ecommerce.repository;

import com.example.Ecommerce.DTO.responseDto.ProductResponseDto;
import com.example.Ecommerce.Enum.ProductCategory;
import com.example.Ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

  List<Product> findByProductCategory(ProductCategory productCategory);

@Query(value = "select * from product p where p.price > :price and p.product_category = :category",nativeQuery = true)
  List<Product> getAllProductsByPriceAndCategory(int price,String category);
}
