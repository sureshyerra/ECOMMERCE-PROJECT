package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.requestDto.CustomerRequestDto;
import com.example.Ecommerce.DTO.responseDto.CustomerResponseDto;
import com.example.Ecommerce.exception.MobileNoAlreadyExistsException;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException;
}
