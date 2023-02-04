package com.example.book.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String vendorCode;
    private Integer count;
}
