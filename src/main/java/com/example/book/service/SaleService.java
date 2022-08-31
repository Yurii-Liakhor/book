package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.entity.Customer;
import com.example.book.entity.Sale;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CustomerRepository;
import com.example.book.repository.SalesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Log4j2
@Service
public class SaleService {
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final SalesRepository salesRepository;

    public SaleService(BookRepository bookRepository, CustomerRepository customerRepository, SalesRepository salesRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.salesRepository = salesRepository;
    }

    @Transactional
    public void saleBook(String vendorCode, int bookCount, String userName) {
        Book book = bookRepository.getBookByVendorCode(vendorCode);
        if(book.getCount() < bookCount) {
            throw new IllegalArgumentException("not enough books");
        }
        book.setCount(book.getCount() - bookCount);
        bookRepository.save(book);

        Customer customer = customerRepository.getCustomerByUserName(userName);

        Sale sale = Sale.builder()
                .userName(customer.getUserName())
                .saleDate(LocalDateTime.now())
                .count(bookCount)
                .build();

        salesRepository.save(sale);
    }
}
