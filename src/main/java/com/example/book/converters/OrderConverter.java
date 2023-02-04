package com.example.book.converters;

import com.example.book.entity.Order;
import com.example.book.model.ItemDto;
import com.example.book.model.OrderItemDto;

import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderItemDto orderToOrderItemDto(Order order) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderCode(order.getOrderCode());
        orderItemDto.setSaleDate(order.getSaleDate());
        orderItemDto.setItems(order.getItems().stream().map(item -> ItemDto.builder()
                .count(item.getCount())
                .vendorCode(item.getVendorCode())
                .build()).collect(Collectors.toList()));
        return orderItemDto;
    }
}
