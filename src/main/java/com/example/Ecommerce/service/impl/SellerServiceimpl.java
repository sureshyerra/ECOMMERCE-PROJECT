package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTO.requestDto.SellerRequestDto;
import com.example.Ecommerce.DTO.responseDto.SellerResponseDto;
import com.example.Ecommerce.entity.Seller;
import com.example.Ecommerce.exception.EmailAlreadyPresentException;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.SellerService;
import com.example.Ecommerce.transformer.sellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceimpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

//        Seller seller = new Seller();
//        seller.setAge(sellerRequestDto.getAge());
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobileNo(sellerRequestDto.getMobileNo());


        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null){
            throw new EmailAlreadyPresentException("email is already registered");
        }

        Seller seller = sellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
       Seller savedSeller =  sellerRepository.save(seller);

        //prepare responsedto
        SellerResponseDto sellerResponseDto = sellerTransformer.SellerToSellerRequestDto(savedSeller);
        return sellerResponseDto;



    }
}
