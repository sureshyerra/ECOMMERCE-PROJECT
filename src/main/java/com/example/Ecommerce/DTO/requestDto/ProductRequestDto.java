package com.example.Ecommerce.DTO.requestDto;

import com.example.Ecommerce.Enum.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    int sellerId;

    String productName;

    int quantity;

    int price;

    ProductCategory productCategory;

}
