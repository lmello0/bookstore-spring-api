package com.lmello.bookstore.repository;

import com.lmello.bookstore.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, String> {
    Optional<Author> findByNameIgnoreCase(String name);

    Optional<Author> findByName(String name);

    Page<Author> findAll(Pageable pagination);
}
