package com.example.Ecommerce.DTO.responseDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {
    String orderNo;

    int totalValue;

    Date orderDate;

    String cardUsed;

    List<ItemResponseDto> items;

    String customerName;
}
