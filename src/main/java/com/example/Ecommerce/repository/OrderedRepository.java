package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
