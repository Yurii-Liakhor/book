package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.entity.Customer;
import com.example.book.entity.Order;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CustomerRepository;
import com.example.book.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
@Service
public class SaleService {
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public SaleService(BookRepository bookRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void saleBook(String vendorCode, int bookCount, String userName) {
        Book book = bookRepository.getBookByVendorCode(vendorCode).orElseThrow(() -> {
            return new NullPointerException("Book by vendor code: " + vendorCode + ", was not found.");
        });
        if(book.getCount() < bookCount) {
            throw new IllegalArgumentException("not enough books");
        }
        book.setCount(book.getCount() - bookCount);
        bookRepository.save(book);

        Customer customer = customerRepository.getCustomerByUserName(userName).orElseThrow(() -> {
            return new NullPointerException("Customer by user name: " + userName + ", was not found.");
        });

        Order order = Order.builder()
                .order(UUID.randomUUID().toString())
                .userName(customer.getUserName())
                .saleDate(LocalDateTime.now())
                .count(bookCount)
                .build();

        orderRepository.save(order);
    }

    @Transactional
    public void refundBook(String order, String userName) {
        Order sale = orderRepository.getSaleByOrderAndUserName(order, userName).orElseThrow(() -> {
            return new NullPointerException("Sale was not found");
        });

        Customer customer = customerRepository.getCustomerByUserName(userName).orElseThrow(() -> {
            return new NullPointerException("Customer by user name: " + userName + ", was not found.");
        });

        orderRepository.save(sale);
    }

}
