package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTO.requestDto.CardRequestDto;
import com.example.Ecommerce.DTO.responseDto.CardResponseDto;
import com.example.Ecommerce.entity.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){


        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate()).build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card){

        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .build();
    }
}
