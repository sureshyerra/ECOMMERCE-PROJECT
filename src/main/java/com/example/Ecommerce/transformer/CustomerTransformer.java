package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTO.requestDto.CustomerRequestDto;
import com.example.Ecommerce.DTO.responseDto.CustomerResponseDto;
import com.example.Ecommerce.entity.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .age(customerRequestDto.getAge())
                .address(customerRequestDto.getAddress())
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .mobileNo(customerRequestDto.getMobileNo())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("welcome " + customer.getName() + " to Amazon")
                .build();
    }
}
