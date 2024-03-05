package com.lmello.bookstore.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AuthorDTO(
        String id,
        @JsonProperty
        @NotBlank
        String name
) {
        public AuthorDTO(String name) {
                this(null, name);
        }
}
