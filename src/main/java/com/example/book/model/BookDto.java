package com.example.book.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String vendorCode;
    private String name;
    private String author;
    private LocalDate year;
    private Integer count;
    private BigDecimal price;
}