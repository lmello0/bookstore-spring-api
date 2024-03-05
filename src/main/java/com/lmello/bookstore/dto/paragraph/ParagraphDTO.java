package com.lmello.bookstore.dto.paragraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParagraphDTO(
        @JsonProperty
        @NotNull
        int paragraphNumber,

        @JsonProperty
        @NotBlank
        String paragraph
) {
}
