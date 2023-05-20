package com.example.Ecommerce.entity;

import com.example.Ecommerce.Enum.ProductCategory;
import com.example.Ecommerce.Enum.ProductStatus;
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
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int quantity;

    int price;
    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;


    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> ItemList = new ArrayList<>();
}
