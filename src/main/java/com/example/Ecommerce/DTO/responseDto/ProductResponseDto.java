package com.example.Ecommerce.DTO.responseDto;

import com.example.Ecommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String productName;

    String sellerName;

    int Quantity;

    ProductStatus productStatus;


}
