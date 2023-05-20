package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.requestDto.CardRequestDto;
import com.example.Ecommerce.DTO.responseDto.CardResponseDto;
import com.example.Ecommerce.exception.InvalidCustomerException;

public interface CardService {


    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
