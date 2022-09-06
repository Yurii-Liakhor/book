package com.example.book.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime saleDate;
    @Column(nullable = false)
    private String userName;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Item> items;
    @Column(nullable = false, updatable = false)
    private String orderCode;

    public Order(LocalDateTime saleDate, String userName) {
        this.orderCode = UUID.randomUUID().toString();
        this.saleDate = saleDate;
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        if (!getSaleDate().equals(order.getSaleDate())) return false;
        if (!getUserName().equals(order.getUserName())) return false;
        return getOrderCode().equals(order.getOrderCode());
    }

    @Override
    public int hashCode() {
        int result = getSaleDate().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getOrderCode().hashCode();
        return result;
    }
}
