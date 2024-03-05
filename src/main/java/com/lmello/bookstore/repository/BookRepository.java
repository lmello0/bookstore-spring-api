package com.lmello.bookstore.repository;

import com.lmello.bookstore.model.Author;
import com.lmello.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByTitleAndAuthor(String title, Author author);

    List<Book> findAllByActive(boolean getDeleted);
}
