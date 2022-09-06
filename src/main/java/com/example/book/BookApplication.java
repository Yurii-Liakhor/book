package com.example.book;

import com.example.book.entity.Book;
import com.example.book.entity.Customer;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CustomerRepository;
import com.example.book.service.SaleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
@SpringBootApplication
public class BookApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Autowired
	private SaleService saleService;

	@Bean
	public CommandLineRunner initBD(BookRepository bookRepository, CustomerRepository customerRepository) {
		return (args) -> {
			log.info("initBD");

			List<Book> books = Arrays.asList(
					Book.builder().author("Dumbledore").count(10).name("book1").vendorCode("111111").year(LocalDate.ofYearDay(2022, 1)).build(),
					Book.builder().author("Bilbo").count(5).name("Roman").vendorCode("222222").year(LocalDate.ofYearDay(22, 1)).build()
			);
			bookRepository.saveAll(books);

			List<Customer> customers = Collections.singletonList(
					Customer.builder().userName("Yurii").build()
			);
			customerRepository.saveAll(customers);

			saleService.saleBook("111111", 2, "Yurii");
//			saleService.refundBook("8335b196-b3ed-478d-9e9a-66f8e7a2d65b", "Yurii");
		};
	}
}
