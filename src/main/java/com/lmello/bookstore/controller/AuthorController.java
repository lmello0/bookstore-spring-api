package com.lmello.bookstore.controller;

import com.lmello.bookstore.dto.author.AuthorDTO;
import com.lmello.bookstore.model.Author;
import com.lmello.bookstore.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createAuthor(@Valid @RequestBody AuthorDTO authorDTO, UriComponentsBuilder uriComponentsBuilder) {
        Author author = authorService.createAuthor(authorDTO);

        URI uri = uriComponentsBuilder
                .path("/author/{name}")
                .buildAndExpand(author.getName())
                .toUri();

        AuthorDTO returnAuthor = new AuthorDTO(author.getId(), author.getName());

        return ResponseEntity.created(uri).body(returnAuthor);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findAuthor(@PathVariable String name) {
        return ResponseEntity.ok(authorService.findByName(name));
    }

    @GetMapping
    public ResponseEntity<?> findAllAuthors(@PageableDefault Pageable pagination) {
        Page<Author> authors = authorService.findAllAuthors(pagination);

        return ResponseEntity.ok(authors);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateAuthor(@PathVariable String id, @Valid @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDTO));
    }
}
