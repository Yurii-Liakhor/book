package com.example.book;

import com.example.book.service.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookApplicationTests {

	@Autowired
	SaleService saleService;

	@Test
	void saleBook() {
		saleService.saleBook("111111", 1, "Yurii");
	}

}
