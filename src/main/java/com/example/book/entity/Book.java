package com.example.book.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String vendorCode;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private LocalDate year;
    @Column(nullable = false)
    private Integer count;
    @Column(nullable = false)
    private BigDecimal price;

    public Book(String vendorCode, String name, String author, LocalDate year, Integer count, BigDecimal price) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.author = author;
        this.year = year;
        this.count = count;
        this.price = price;
    }

    public void incrementBooks(int count) {
        this.count = this.count + count;
    }

    public void decrementBooks(int count) {
        this.count = this.count - count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (!getVendorCode().equals(book.getVendorCode())) return false;
        if (!getName().equals(book.getName())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        if (!getYear().equals(book.getYear())) return false;
        if (!getCount().equals(book.getCount())) return false;
        return getPrice().equals(book.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getVendorCode().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getYear().hashCode();
        result = 31 * result + getCount().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
