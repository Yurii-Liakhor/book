package com.example.book.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDto {
    private String vendorCode;
    private Integer count;
}
