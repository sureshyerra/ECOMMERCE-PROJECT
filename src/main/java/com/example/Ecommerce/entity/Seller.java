package com.example.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    @Column(unique = true)
    String emailId;

    int age;

    String mobileNo;

    @OneToMany(mappedBy = "seller",cascade =  CascadeType.ALL)
    List<Product>products = new ArrayList<>();
}
