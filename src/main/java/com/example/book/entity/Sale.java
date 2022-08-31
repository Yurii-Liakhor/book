package com.example.book.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime saleDate;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private Integer count;

    public Sale(LocalDateTime saleDate, String userName, Integer count) {
        this.saleDate = saleDate;
        this.userName = userName;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale sale)) return false;

        if (!saleDate.equals(sale.saleDate)) return false;
        return userName.equals(sale.userName);
    }

    @Override
    public int hashCode() {
        int result = saleDate.hashCode();
        result = 31 * result + userName.hashCode();
        return result;
    }
}
