package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.requestDto.ItemRequestDto;
import com.example.Ecommerce.entity.Item;
import com.example.Ecommerce.exception.InvalidCustomerException;
import com.example.Ecommerce.exception.InvalidProductException;

public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
