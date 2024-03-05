package com.lmello.bookstore.service;

import com.lmello.bookstore.dto.author.AuthorDTO;
import com.lmello.bookstore.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    Author createAuthor(AuthorDTO authorDTO);

    Page<Author> findAllAuthors(Pageable pagination);

    List<Author> findByName(String name);

    Author findByNameOrCreate(AuthorDTO authorDTO);

    Author updateAuthor(String id, AuthorDTO authorDTO);
}
