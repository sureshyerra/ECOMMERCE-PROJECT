package com.example.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ordered")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderNo;
    @CreationTimestamp
    Date orderDate;

    int totalValue;

    String cardUsed;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item>items = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;
}
