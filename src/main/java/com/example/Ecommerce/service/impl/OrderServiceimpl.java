package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTO.requestDto.OrderRequestDto;
import com.example.Ecommerce.DTO.responseDto.ItemResponseDto;
import com.example.Ecommerce.DTO.responseDto.OrderResponseDto;
import com.example.Ecommerce.Enum.ProductStatus;
import com.example.Ecommerce.entity.*;
import com.example.Ecommerce.exception.InvalidCardException;
import com.example.Ecommerce.exception.InvalidCustomerException;
import com.example.Ecommerce.exception.InvalidProductException;
import com.example.Ecommerce.repository.CardRepository;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.OrderedRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.OrderService;
import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.transformer.ItemTransformer;
import com.example.Ecommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceimpl implements OrderService {
    @Autowired ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRespository;
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private JavaMailSender emailSender;


    public Ordered placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItems()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new Exception("Product Out of stock");
            }
        }
        order.setItems(orderedItems);
        for(Item item: orderedItems)
            item.setOrder(order);
        order.setTotalValue(cart.getCartTotal());
        return order;
    }

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new InvalidProductException("Product doesn't exist");
        }

        Card card = cardRespository.findByCardNo(orderRequestDto.getCardNo());
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity() * product.getPrice());
        order.getItems().add(item);

        customer.getOrderList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order); // order and item

        OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(savedOrder);
        orderResponseDto.setCustomerName(customer.getName());


        List<ItemResponseDto> items = new ArrayList<>();
        for(Item itemEntity: savedOrder.getItems()){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);

            items.add(itemResponseDto);
        }

        orderResponseDto.setItems(items);
        String text = "congrats " + customer.getName() + " your orderNo " + savedOrder.getOrderNo() + " orderedOn " + savedOrder.getOrderDate() + " have been sucessfully booked";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yerraraja26@gmail.com");
        message.setTo(customer.getEmailId());
        message.setSubject("Order Booked");
        message.setText(text);
        emailSender.send(message);
        return orderResponseDto;


    }





    public String generateMaskedCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0;i<cardNo.length()-4;i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;

    }

}
