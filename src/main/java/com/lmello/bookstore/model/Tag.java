package com.lmello.bookstore.model;

import com.lmello.bookstore.dto.tag.TagDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Table(name = "tags")
@Entity(name = "Tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<Book> books = new HashSet<>();

    public Tag(TagDTO tagDTO) {
        this.tagName = tagDTO.tagName();
    }

    @Override
    public String toString() {
        return this.tagName;
    }
}
