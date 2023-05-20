package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTO.requestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.DTO.responseDto.CartResponseDto;
import com.example.Ecommerce.DTO.responseDto.ItemResponseDto;
import com.example.Ecommerce.DTO.responseDto.OrderResponseDto;
import com.example.Ecommerce.entity.*;
import com.example.Ecommerce.exception.InvalidCardException;
import com.example.Ecommerce.exception.InvalidCustomerException;
import com.example.Ecommerce.repository.CardRepository;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.OrderedRepository;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.OrderService;
import com.example.Ecommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceimpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired OrderService orderService;
    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    CardRepository cardRespository;

    public CartResponseDto saveCart(int customerId, Item item){

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal() + item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItems(savedCart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item itemEntity: savedCart.getItems()){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }



    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer id is invalid!!!");
        }

        Card card = cardRespository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is empty!!");
        }

        try{
            Ordered order = orderService.placeOrder(customer,card);  // throw exception if product goes out of stock
            customer.getOrderList().add(order);
            resetCart(cart);
            Ordered savedOrder = orderedRepository.save(order);

            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItems()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItems().clear();

    }
}



