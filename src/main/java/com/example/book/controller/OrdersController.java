package com.example.book.controller;

import com.example.book.converters.OrderConverter;
import com.example.book.entity.Order;
import com.example.book.model.BookDto;
import com.example.book.model.ItemDto;
import com.example.book.model.OrderDto;
import com.example.book.model.OrderItemDto;
import com.example.book.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("orders")
public class OrdersController {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = "getAll")
    public List<OrderDto> getOrders() {
        log.info("getAll");
        return orderRepository.getOrdersByUserName("Yurii").stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .sorted(Comparator.comparing(OrderDto::getSaleDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "getAll", params = "fields=items")
    public List<Object> getOrdersWithItems() {
        log.info("getAll items");
        return orderRepository.getOrdersByUserNameWithItems("Yurii").stream()
                .map(OrderConverter::orderToOrderItemDto)
                .sorted(Comparator.comparing(OrderItemDto::getSaleDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }
}
