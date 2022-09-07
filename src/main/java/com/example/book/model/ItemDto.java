package com.example.book.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Builder
public class ItemDto {
    private String vendorCode;
    private Integer count;
}
