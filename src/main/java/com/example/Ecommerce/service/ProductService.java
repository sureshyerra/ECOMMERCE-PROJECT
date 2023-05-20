package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.requestDto.ProductRequestDto;
import com.example.Ecommerce.DTO.responseDto.ProductResponseDto;
import com.example.Ecommerce.Enum.ProductCategory;
import com.example.Ecommerce.entity.Item;
import com.example.Ecommerce.entity.Ordered;
import com.example.Ecommerce.exception.InvalidSellerException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;


    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String productCategory);

    public  void decreaseProductQuantity(Item item) throws Exception;
}
