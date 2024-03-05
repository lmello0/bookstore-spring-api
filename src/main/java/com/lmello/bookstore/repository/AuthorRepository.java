package com.lmello.bookstore.repository;

import com.lmello.bookstore.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, String> {
    Optional<Author> findByName(String name);

    List<Author> findByNameStartsWithIgnoreCaseOrderByName(String name);

    Page<Author> findAll(Pageable pagination);
}
