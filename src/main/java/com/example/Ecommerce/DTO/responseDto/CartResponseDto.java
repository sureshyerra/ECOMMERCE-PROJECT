package com.example.Ecommerce.DTO.responseDto;

import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.entity.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    int cartTotal;

    int numberOfItems;

    String customerName;

    List<ItemResponseDto> items;
}
