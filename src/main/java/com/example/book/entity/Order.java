package com.example.book.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime saleDate;
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, updatable = false)
    private String order;
    @Column(nullable = false)
    private Integer count;

    public Order(LocalDateTime saleDate, String userName, Integer count) {
        this.order = UUID.randomUUID().toString();
        this.saleDate = saleDate;
        this.userName = userName;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!getSaleDate().equals(order.getSaleDate())) return false;
        if (!getUserName().equals(order.getUserName())) return false;
        return getOrder().equals(order.getOrder());
    }

    @Override
    public int hashCode() {
        int result = getSaleDate().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getOrder().hashCode();
        return result;
    }
}
