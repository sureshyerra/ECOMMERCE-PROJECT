package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTO.requestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.DTO.requestDto.ItemRequestDto;
import com.example.Ecommerce.DTO.responseDto.CartResponseDto;
import com.example.Ecommerce.DTO.responseDto.OrderResponseDto;
import com.example.Ecommerce.entity.Item;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try{
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkOut")

    public OrderResponseDto checkOutCart(@RequestBody CheckoutCartRequestDto checkOutCartRequestdto) throws Exception {

        return cartService.checkOutCart(checkOutCartRequestdto);

    }

}
