package com.example.book.repository;

import com.example.book.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> getOrderByOrderCode(String orderCode);
    Optional<Order> getOrderByOrderCodeAndUserName(String orderCode, String userName);
    List<Order> getOrdersByUserName(String userName);
    @Query("select o from Order o left join fetch o.items where o.userName = :userName")
    List<Order> getOrdersByUserNameWithItems(String userName);
}
