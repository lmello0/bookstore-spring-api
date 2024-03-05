package com.lmello.bookstore.model;

import com.lmello.bookstore.dto.book.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Table(name = "books")
@Entity(name = "Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String url;

    private String synopsis;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "tags_books",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany
    private List<Paragraph> paragraphs;

    public Book(BookDTO bookDTO, Author author, Set<Tag> tags) {
        this.title = bookDTO.title();
        this.url = bookDTO.url();
        this.synopsis = bookDTO.synopsis();
        this.active = true;

        this.author = author;
        this.tags = tags;
    }
}
