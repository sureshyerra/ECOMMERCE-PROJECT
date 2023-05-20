package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTO.responseDto.CartResponseDto;
import com.example.Ecommerce.entity.Cart;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart){

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();

    }
}
