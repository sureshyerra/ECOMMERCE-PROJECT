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
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String emailId;

    int age;
    @Column(unique = true)
    String mobileNo;

    String address;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Ordered> orderList = new ArrayList<>();
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();
    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

}
