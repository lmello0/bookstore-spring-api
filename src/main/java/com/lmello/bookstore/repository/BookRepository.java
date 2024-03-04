package com.lmello.bookstore.repository;

import com.lmello.bookstore.model.Book;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
@EnableScanCount
public interface BookRepository extends CrudRepository<Book, String> {
    Page<Book> findAllByActive(boolean getDeleted, Pageable pagination);

    List<Book> findAllByActiveTrue();

    Optional<Book> findByAuthorAndTitle(String author, String title);
}
