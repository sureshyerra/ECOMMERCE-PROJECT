package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTO.requestDto.CustomerRequestDto;
import com.example.Ecommerce.DTO.responseDto.CustomerResponseDto;
import com.example.Ecommerce.exception.MobileNoAlreadyExistsException;
import com.example.Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @PostMapping("/add")

    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException {

        return customerService.addCustomer(customerRequestDto);

    }
}
