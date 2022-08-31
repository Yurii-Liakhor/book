package com.example.book.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
    @Column(nullable = false)
    private String vendorCode;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private LocalDate year;
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer count;

    public Book(String name, String author, LocalDate year, Integer count) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (!getVendorCode().equals(book.getVendorCode())) return false;
        if (!getName().equals(book.getName())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        return getYear().equals(book.getYear());
    }

    @Override
    public int hashCode() {
        int result = getVendorCode().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getYear().hashCode();
        return result;
    }
}
