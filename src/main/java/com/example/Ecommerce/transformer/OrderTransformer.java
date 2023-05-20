package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTO.responseDto.OrderResponseDto;
import com.example.Ecommerce.entity.Ordered;

public class OrderTransformer {
    public static OrderResponseDto OrderToOrderResponseDto(Ordered savedOrder){
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderDate(savedOrder.getOrderDate())
                .cardUsed(savedOrder.getCardUsed())
                .orderNo(savedOrder.getOrderNo())
                .totalValue(savedOrder.getTotalValue()).build();
        return orderResponseDto;
    }
}
