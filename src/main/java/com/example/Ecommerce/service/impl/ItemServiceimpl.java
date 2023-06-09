package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTO.requestDto.ItemRequestDto;
import com.example.Ecommerce.Enum.ProductStatus;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.entity.Item;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.exception.InvalidCustomerException;
import com.example.Ecommerce.exception.InvalidProductException;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.ItemRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.ItemService;
import com.example.Ecommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceimpl implements ItemService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new InvalidProductException("Product doesn't exist");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product out of Stock");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        // item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);
        return itemRepository.save(item);


    }
}
