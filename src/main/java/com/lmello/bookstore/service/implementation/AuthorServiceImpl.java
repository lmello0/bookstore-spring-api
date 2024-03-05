package com.lmello.bookstore.service.implementation;

import com.lmello.bookstore.dto.author.AuthorDTO;
import com.lmello.bookstore.exception.DuplicateEntryException;
import com.lmello.bookstore.exception.NotFoundException;
import com.lmello.bookstore.model.Author;
import com.lmello.bookstore.repository.AuthorRepository;
import com.lmello.bookstore.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(AuthorDTO authorDTO) {
        if (authorRepository.findByName(authorDTO.name()).isPresent()) {
            throw new DuplicateEntryException("\"" + authorDTO.name() + "\" already exists");
        }

        return authorRepository.save(new Author(authorDTO));
    }

    @Override
    public Page<Author> findAllAuthors(Pageable pagination) {
        return authorRepository.findAll(pagination);
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findByNameStartsWithIgnoreCaseOrderByName(name);
    }

    @Override
    public Author findByNameOrCreate(AuthorDTO authorDTO) {
        Optional<Author> author = authorRepository.findByName(authorDTO.name());

        return author.orElseGet(() -> createAuthor(authorDTO));
    }

    @Override
    public Author updateAuthor(String id, AuthorDTO authorDTO) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            throw new NotFoundException("The author does not exists");
        }

        BeanUtils.copyProperties(authorDTO, author.get(), "id");

        return authorRepository.save(author.get());
    }
}
