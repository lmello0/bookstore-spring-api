package com.lmello.bookstore.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmello.bookstore.model.Book;
import com.lmello.bookstore.model.Tag;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public record BookResponseDTO(
        String id,

        @JsonProperty
        @NotBlank
        String author,

        @JsonProperty
        @NotBlank
        String title,

        @JsonProperty
        @NotBlank
        String synopsis,

        @JsonProperty
        @NotBlank
        String url,

        @JsonProperty
        List<String> tags,

        @JsonProperty
        Boolean active
) {
    public BookResponseDTO(Book book) {
        this(
                book.getId(),
                book.getAuthor().getName(),
                book.getTitle(),
                book.getSynopsis(),
                book.getUrl(),
                new ArrayList<>(book.getTags().stream().map(Tag::toString).toList()),
                book.isActive()
        );
    }
}
