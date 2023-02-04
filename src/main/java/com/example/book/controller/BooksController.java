package com.example.book.controller;

import com.example.book.entity.Book;
import com.example.book.model.BookDto;
import com.example.book.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("books")
public class BooksController {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("add")
    public void add(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
    }

    @PutMapping("edit")
    public void edit(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam("code") String vendorCode) {
        bookRepository.deleteByVendorCode(vendorCode);
    }

    @GetMapping("getAll")
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .sorted(Comparator.comparing(BookDto::getName, Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }
}
