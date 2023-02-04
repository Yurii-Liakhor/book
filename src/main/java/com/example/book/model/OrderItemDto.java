package com.example.book.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private String orderCode;
    private LocalDateTime saleDate;
    private List<ItemDto> items;
}
