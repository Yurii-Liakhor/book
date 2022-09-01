package com.example.book.repository;

import com.example.book.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> getSaleByOrder(String order);

    Optional<Order> getSaleByOrderAndUserName(String order, String userName);
}
