package com.lmello.bookstore.service.implementation;

import com.lmello.bookstore.dto.BookDTO;
import com.lmello.bookstore.exception.DuplicateEntryException;
import com.lmello.bookstore.exception.NotFoundException;
import com.lmello.bookstore.model.Book;
import com.lmello.bookstore.repository.BookRepository;
import com.lmello.bookstore.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(BookDTO bookDTO) {
        if (bookRepository.findByAuthorAndTitle(bookDTO.author(), bookDTO.title()).isPresent()) {
            throw new DuplicateEntryException("\"" + bookDTO.title() + "\" by \"" + bookDTO.author() + "\" already exists");
        }

        return bookRepository.save(new Book(bookDTO));
    }

    @Override
    public Page<Book> findAllBooks(boolean getDeleted, Pageable pagination) {
        return bookRepository.findAllByActive(getDeleted, pagination);
    }

    @Override
    public Book findRandomBook() {
        List<Book> books = bookRepository.findAllByActiveTrue();

        int randomIndex = new Random().nextInt(0, books.size());

        return books.get(randomIndex);
    }

    @Override
    public Book findById(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new NotFoundException("Book \"" + id + "\" not found");
        }

        return book.get();
    }

    @Override
    public Book updateBook(String bookId, BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty() || !book.get().getActive()) {
            throw new NotFoundException("The book does not exists");
        }

        BeanUtils.copyProperties(bookDTO, book.get(), "id");

        book.get().setActive(true);

        return bookRepository.save(book.get());
    }

    @Override
    public void disableBook(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            throw new NotFoundException("The book does not exists");
        }

        book.get().setActive(false);
    }
}
