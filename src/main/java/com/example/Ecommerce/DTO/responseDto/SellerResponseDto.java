package com.example.Ecommerce.DTO.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerResponseDto {

    String name;

    int age;
}
