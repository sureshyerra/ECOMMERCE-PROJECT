package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.requestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.DTO.responseDto.CartResponseDto;
import com.example.Ecommerce.DTO.responseDto.OrderResponseDto;
import com.example.Ecommerce.entity.Item;

public interface CartService {

    public CartResponseDto saveCart(int customerId, Item item);

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkOutCartRequestdto) throws Exception;
}
