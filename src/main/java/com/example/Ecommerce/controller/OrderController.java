package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTO.requestDto.OrderRequestDto;
import com.example.Ecommerce.DTO.responseDto.OrderResponseDto;
import com.example.Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")

    public OrderResponseDto placeDirectOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {

        return orderService.placeOrder(orderRequestDto);
    }
}
