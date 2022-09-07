package com.example.book.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String vendorCode;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()//name = "order_id"
    private Order order;

    public Item(String vendorCode, BigDecimal price, Integer count) {
        this.vendorCode = vendorCode;
        this.price = price;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;

        if (!getVendorCode().equals(item.getVendorCode())) return false;
        if (!getPrice().equals(item.getPrice())) return false;
        return getCount().equals(item.getCount());
    }

    @Override
    public int hashCode() {
        int result = getVendorCode().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getCount().hashCode();
        return result;
    }
}
