package com.example.Ecommerce.DTO.requestDto;

import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data

@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerRequestDto {

    String name;

    String emailId;

    int age;

    String mobileNo;

    String address;
}
