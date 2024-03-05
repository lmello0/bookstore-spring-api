package com.lmello.bookstore.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmello.bookstore.dto.paragraph.ParagraphDTO;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BookDTO(
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
        List<ParagraphDTO> paragraphs,

        @JsonProperty
        Boolean active
) {
}
