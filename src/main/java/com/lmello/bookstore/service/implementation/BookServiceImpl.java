package com.lmello.bookstore.service.implementation;

import com.lmello.bookstore.dto.author.AuthorDTO;
import com.lmello.bookstore.dto.book.BookDTO;
import com.lmello.bookstore.dto.book.BookResponseDTO;
import com.lmello.bookstore.dto.tag.TagDTO;
import com.lmello.bookstore.exception.DuplicateEntryException;
import com.lmello.bookstore.exception.NotFoundException;
import com.lmello.bookstore.model.Author;
import com.lmello.bookstore.model.Book;
import com.lmello.bookstore.model.Tag;
import com.lmello.bookstore.repository.BookRepository;
import com.lmello.bookstore.service.AuthorService;
import com.lmello.bookstore.service.BookService;
import com.lmello.bookstore.service.ParagraphService;
import com.lmello.bookstore.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final AuthorService authorService;

    private final TagService tagService;

    private final ParagraphService paragraphService;

    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorService authorService, TagService tagService, BookRepository bookRepository, ParagraphService paragraphService) {
        this.authorService = authorService;
        this.tagService = tagService;
        this.bookRepository = bookRepository;
        this.paragraphService = paragraphService;
    }

    @Override
    public BookResponseDTO createBook(BookDTO bookDTO) {
        Author bookAuthor = authorService.findByNameOrCreate(new AuthorDTO(bookDTO.author()));

        if (bookRepository.findByTitleAndAuthor(bookDTO.title(), bookAuthor).isPresent()) {
            throw new DuplicateEntryException("\"" + bookDTO.title() + "\" by \"" + bookDTO.author() + "\" already exists");
        }

        Set<Tag> tags = bookDTO.tags()
                .stream()
                .map((tag) -> tagService.findByNameOrCreate(new TagDTO(tag)))
                .collect(Collectors.toSet());

        Book newBook = bookRepository.save(new Book(bookDTO, bookAuthor, tags));

        paragraphService.createParagraphs(bookDTO.paragraphs(), newBook);

        return new BookResponseDTO(newBook);
    }

    @Override
    public Page<BookResponseDTO> findAllBooks(boolean getDeleted, Pageable pagination) {
        List<Book> books = bookRepository.findAllByActive(getDeleted);

        int pageStart = (int) pagination.getOffset();
        int pageEnd = Math.min((pageStart + pagination.getPageSize()), books.size());

        List<BookResponseDTO> pageContent = books
                .subList(pageStart, pageEnd)
                .stream().map(BookResponseDTO::new)
                .toList();

        return new PageImpl<>(pageContent, pagination, books.size());
    }

    @Override
    public BookResponseDTO findById(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new NotFoundException("\"" + id + "\" not found");
        }

        return new BookResponseDTO(book.get());
    }

    @Override
    public BookResponseDTO findRandomBook() {
        List<Book> books = bookRepository.findAllByActive(true);

        int randomIndex = new Random().nextInt(0, books.size());

        return new BookResponseDTO(books.get(randomIndex));
    }

    @Override
    public BookResponseDTO updateBook(String id, BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty() || !book.get().isActive()) {
            throw new NotFoundException("\"" + id + "\" not found");
        }

        BeanUtils.copyProperties(bookDTO, book.get(),"id", "active");
        book.get().setActive(true);

        paragraphService.updateParagraphs(bookDTO.paragraphs(), book.get());

        return new BookResponseDTO(book.get());
    }

    @Override
    public void disableBook(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty() || !book.get().isActive()) {
            throw new NotFoundException("\"" + id + "\" not found");
        }

        book.get().setActive(false);
    }
}
