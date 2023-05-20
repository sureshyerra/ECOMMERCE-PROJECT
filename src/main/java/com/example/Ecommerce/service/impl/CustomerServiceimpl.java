package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTO.requestDto.CustomerRequestDto;
import com.example.Ecommerce.DTO.responseDto.CustomerResponseDto;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.exception.MobileNoAlreadyExistsException;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CustomerService;
import com.example.Ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceimpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException {

        if(customerRepository.findByMobileNo(customerRequestDto.getEmailId()) != null){
            throw new MobileNoAlreadyExistsException("mobile no already exists");
        }

        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

           Cart cart =  Cart.builder()
                   .cartTotal(0)
                   .numberOfItems(0)
                   .customer(customer)
                   .build();

           customer.setCart(cart);

          Customer  savedCustomer =  customerRepository.save(customer);

          return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);


    }
}
