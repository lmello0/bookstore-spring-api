package com.lmello.bookstore.model;

import com.lmello.bookstore.dto.author.AuthorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "authors")
@Entity(name = "Author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.name();
    }
}
