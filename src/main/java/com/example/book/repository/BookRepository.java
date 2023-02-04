package com.example.book.repository;

import com.example.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByAuthor(String author);
    Optional<Book> getBookByVendorCode(String vendorCode);
    @Transactional
    void deleteByVendorCode(String vendorCode);
}
