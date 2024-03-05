package com.lmello.bookstore.repository;

import com.lmello.bookstore.model.Book;
import com.lmello.bookstore.model.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParagraphRepository extends JpaRepository<Paragraph, String> {
    void deleteAllByBook(Book book);
}
