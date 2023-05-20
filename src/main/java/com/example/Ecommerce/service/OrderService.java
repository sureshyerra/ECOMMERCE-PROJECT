package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.requestDto.OrderRequestDto;
import com.example.Ecommerce.DTO.responseDto.OrderResponseDto;
import com.example.Ecommerce.entity.Card;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.entity.Ordered;
import com.example.Ecommerce.exception.InvalidCardException;
import com.example.Ecommerce.exception.InvalidCustomerException;
import com.example.Ecommerce.exception.InvalidProductException;

public interface OrderService {

    public Ordered placeOrder(Customer customer, Card card) throws Exception;


    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
