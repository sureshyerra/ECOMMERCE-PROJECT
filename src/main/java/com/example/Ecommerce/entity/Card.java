package com.example.Ecommerce.entity;

import com.example.Ecommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cvv;

    @Column(unique = true)
    String cardNo;

    Date expiryDate;
    @Enumerated(EnumType.STRING)
    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
