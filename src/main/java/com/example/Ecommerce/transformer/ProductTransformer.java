package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTO.requestDto.ProductRequestDto;
import com.example.Ecommerce.DTO.responseDto.ProductResponseDto;
import com.example.Ecommerce.Enum.ProductStatus;
import com.example.Ecommerce.entity.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder().name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .quantity(productRequestDto.getQuantity())
                .build();
    }

    public static ProductResponseDto ProductToPublicResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .Quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
