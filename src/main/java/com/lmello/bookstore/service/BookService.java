package com.lmello.bookstore.service;

import com.lmello.bookstore.dto.book.BookDTO;
import com.lmello.bookstore.dto.book.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDTO createBook(BookDTO bookDTO);

    Page<BookResponseDTO> findAllBooks(boolean getDeleted, Pageable pagination);

    BookResponseDTO findById(String id);

    BookResponseDTO findRandomBook();

    BookResponseDTO updateBook(String id, BookDTO bookDTO);

    void disableBook(String id);
}
