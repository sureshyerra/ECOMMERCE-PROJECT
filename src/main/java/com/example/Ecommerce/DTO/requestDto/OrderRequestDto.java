package com.example.Ecommerce.DTO.requestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {

    int customerId;

    int productId;

    int cvv;

    String cardNo;

    int requiredQuantity;
}
