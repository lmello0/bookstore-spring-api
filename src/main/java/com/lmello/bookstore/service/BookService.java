package com.lmello.bookstore.service;

import com.lmello.bookstore.dto.BookDTO;
import com.lmello.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book createBook(BookDTO bookDTO);

    Page<Book> findAllBooks(boolean getDeleted, Pageable pagination);

    Book findRandomBook();

    Book findById(String id);

    Book updateBook(String bookId, BookDTO bookDTO);

    void disableBook(String bookId);
}
