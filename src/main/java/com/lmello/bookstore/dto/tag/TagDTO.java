package com.lmello.bookstore.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record TagDTO(
        String id,

        @JsonProperty
        @NotBlank
        String tagName
) {
        public TagDTO(String tagName) {
                this(null, tagName);
        }
}
