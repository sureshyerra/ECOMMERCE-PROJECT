package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTO.requestDto.ProductRequestDto;
import com.example.Ecommerce.DTO.responseDto.ProductResponseDto;
import com.example.Ecommerce.Enum.ProductCategory;
import com.example.Ecommerce.exception.InvalidSellerException;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
        return productService.addProduct(productRequestDto);

    }

    //get all products of particularcategory

    @GetMapping("/get/{category}")

    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category") ProductCategory category){

        return productService.getAllProductsByCategory(category);
    }

    @GetMapping("/get/{price}/{category}")

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(@PathVariable("price") int price,
                                                             @PathVariable("category")String productCategory){
        return productService.getAllProductsByPriceAndCategory(price,productCategory);
    }
}
