package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTO.requestDto.SellerRequestDto;
import com.example.Ecommerce.DTO.responseDto.SellerResponseDto;
import com.example.Ecommerce.entity.Seller;

public class sellerTransformer {


    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder().name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobileNo(sellerRequestDto.getMobileNo())
                .age(sellerRequestDto.getAge()).build();
    }

    public static SellerResponseDto SellerToSellerRequestDto(Seller seller){

        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();

    }
}
