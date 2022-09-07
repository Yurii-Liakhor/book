package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.entity.Item;
import com.example.book.entity.Order;
import com.example.book.model.ItemDto;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CustomerRepository;
import com.example.book.repository.ItemRepository;
import com.example.book.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class SaleService {
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    public SaleService(BookRepository bookRepository, CustomerRepository customerRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public String saleBook(String vendorCode, int bookCount, String userName) {
        log.info("saleBook");
        Book book = bookRepository.getBookByVendorCode(vendorCode).orElseThrow(() -> {
            return new NullPointerException("Book by vendor code: " + vendorCode + ", not found.");
        });
        if(book.getCount() < bookCount) {
            throw new IllegalArgumentException("Not enough books.");
        }
        book.decrementBooks(bookCount);
        bookRepository.save(book);
        if(!customerRepository.existsByUserName(userName)) {
            throw new NullPointerException("Customer by user name: " + userName + ", not found.");
        }
        Item item = Item.builder()
                .vendorCode(book.getVendorCode())
                .count(bookCount)
                .price(book.getPrice())
                .build();
        Order order = Order.builder()
                .orderCode(UUID.randomUUID().toString())
                .userName(userName)
                .saleDate(LocalDateTime.now())
                .items(List.of(item))
                .build();
        item.setOrder(order);
        itemRepository.save(item);
        orderRepository.save(order);
        return order.getOrderCode();
    }

    @Transactional
    public String saleBooks(List<ItemDto> itemsDto, String userName) {
        log.info("saleBooks");
        if(!customerRepository.existsByUserName(userName)) {
            throw new NullPointerException("Customer by user name: " + userName + ", not found.");
        }
        List<Item> items = new ArrayList<>();
        Order order = Order.builder()
                .orderCode(UUID.randomUUID().toString())
                .userName(userName)
                .saleDate(LocalDateTime.now())
                .build();

        itemsDto.forEach(itemDto -> {
            Book book = bookRepository.getBookByVendorCode(itemDto.getVendorCode()).orElseThrow(() -> {
                throw new NullPointerException("Book by vendor code: " + itemDto.getVendorCode() + ", not found.");
            });
            if(book.getCount() < itemDto.getCount()) {
                throw new IllegalArgumentException("Not enough books.");
            }
            book.decrementBooks(itemDto.getCount());
            bookRepository.save(book);
            items.add(Item.builder()
                            .vendorCode(book.getVendorCode())
                            .count(itemDto.getCount())
                            .price(book.getPrice())
                            .order(order)
                            .build());
        });
        order.setItems(items);
        itemRepository.saveAll(items);
        orderRepository.save(order);
        return order.getOrderCode();
    }

    @Transactional
    public void refundBook(String orderCode, String userName) {
        log.info("refundBook");
        if(!customerRepository.existsByUserName(userName)) {
            throw new NullPointerException("Customer by user name: " + userName + ", was not found.");
        }
        Order order = orderRepository.getSaleByOrderCodeAndUserName(orderCode, userName).orElseThrow(() -> {
            return new NullPointerException("Sale not found.");
        });
        List<Item> items = order.getItems();
        items.forEach(item -> {
            Book book = bookRepository.getBookByVendorCode(item.getVendorCode()).orElseThrow(() -> {
                return new NullPointerException("Book not found.");
            });
            book.incrementBooks(item.getCount());
        });
        orderRepository.delete(order);
    }

}
