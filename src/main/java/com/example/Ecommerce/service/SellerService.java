package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.requestDto.SellerRequestDto;
import com.example.Ecommerce.DTO.responseDto.SellerResponseDto;
import com.example.Ecommerce.exception.EmailAlreadyPresentException;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;
}
