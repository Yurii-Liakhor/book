package com.example.book;

import com.example.book.entity.Book;
import com.example.book.entity.Customer;
import com.example.book.model.ItemDto;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CustomerRepository;
import com.example.book.service.SaleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookApplicationTests {
	@Autowired
	private SaleService saleService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void initData() {
		log.info("initBD");

		List<Book> books = Arrays.asList(
				Book.builder().author("Dumbledore").count(10).name("book1").vendorCode("111111").price(BigDecimal.valueOf(100)).year(LocalDate.ofYearDay(2022, 1)).build(),
				Book.builder().author("Bilbo").count(5).name("Roman").vendorCode("222222").price(BigDecimal.valueOf(250)).year(LocalDate.ofYearDay(22, 1)).build()
		);
		bookRepository.saveAll(books);

		List<Customer> customers = List.of(
				Customer.builder().userName("Yurii").build(),
				Customer.builder().userName("Dima").build(),
				Customer.builder().userName("Vasya").build()
		);
		customerRepository.saveAll(customers);
	}

	@Test
	void saleBook() {
		String lastOrderCode = saleService.saleBook("111111", 1, "Dima");
		log.info("Order code: {}", lastOrderCode);
		Assert.notNull(lastOrderCode, "no order code");
	}


	@Test
	void saleBooks() {
		List<ItemDto> itemsDto = List.of(
				ItemDto.builder()
						.count(3)
						.vendorCode("111111")
						.build(),
				ItemDto.builder()
						.count(2)
						.vendorCode("222222")
						.build()
		);
		String lastOrderCode = saleService.saleBooks(itemsDto, "Yurii");
		log.info("Order code: {}", lastOrderCode);
		Assert.notNull(lastOrderCode, "no order code");
	}

	@Test
	void refundBook() {
//		saleService.refundBook("<orderCode>", "<userName>");
		saleService.refundBook("204f60e4-06c7-4522-90c0-cc00c2e61340", "Dima");
	}
}
