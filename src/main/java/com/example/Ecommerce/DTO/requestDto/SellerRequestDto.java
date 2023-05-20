package com.example.Ecommerce.DTO.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SellerRequestDto {
        String name;

        String emailId;

        int age;

        String mobileNo;
}
