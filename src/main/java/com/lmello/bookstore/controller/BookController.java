package com.lmello.bookstore.controller;

import com.lmello.bookstore.dto.book.BookDTO;
import com.lmello.bookstore.dto.book.BookResponseDTO;
import com.lmello.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDTO bookDTO, UriComponentsBuilder uriComponentsBuilder) {
        BookResponseDTO book = bookService.createBook(bookDTO);

        URI uri = uriComponentsBuilder
                .path("/books/{id}")
                .buildAndExpand(book.id())
                .toUri();

        return ResponseEntity.created(uri).body(book);
    }

    @GetMapping
    public ResponseEntity<?> findAll(
            @PageableDefault Pageable pagination,
            @RequestParam(value = "onlyActive", defaultValue = "true") String getDeleted,
            @RequestParam(value = "random", defaultValue = "false") String getRandom
    ) {
        if (Boolean.parseBoolean(getRandom)) {
            return ResponseEntity.ok(bookService.findRandomBook());
        }

        boolean parsedGetDeleted = Boolean.parseBoolean(getDeleted);

        Page<BookResponseDTO> books = bookService.findAllBooks(parsedGetDeleted, pagination);

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        BookResponseDTO book = bookService.findById(id);

        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody @Valid BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        bookService.disableBook(id);

        return ResponseEntity.noContent().build();
    }
}
