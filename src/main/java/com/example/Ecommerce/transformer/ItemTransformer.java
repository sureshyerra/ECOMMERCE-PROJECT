package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTO.requestDto.ItemRequestDto;
import com.example.Ecommerce.DTO.responseDto.ItemResponseDto;
import com.example.Ecommerce.entity.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){

        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity()).build();
    }
}
