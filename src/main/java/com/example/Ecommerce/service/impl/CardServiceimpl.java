package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTO.requestDto.CardRequestDto;
import com.example.Ecommerce.DTO.responseDto.CardResponseDto;
import com.example.Ecommerce.entity.Card;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.exception.InvalidCustomerException;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CardService;
import com.example.Ecommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceimpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer = customerRepository.findByMobileNo(cardRequestDto.getMobileNo());

        if(customer == null){
            throw new InvalidCustomerException("customer doesnot exists");
        }

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

         customerRepository.save(customer);

         CardResponseDto cardResponseDto = CardTransformer.CardToCardResponseDto(card);

         cardResponseDto.setCustomerName(customer.getName());

         return cardResponseDto;



    }
}
