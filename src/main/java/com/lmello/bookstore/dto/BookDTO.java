package com.lmello.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BookDTO(
        String id,
        @JsonProperty
        @NotNull
        @NotBlank
        String author,

        @JsonProperty
        @NotNull
        @NotBlank
        String title,

        @JsonProperty
        @NotNull
        @NotBlank
        String url,

        @JsonProperty
        @NotNull
        @NotBlank
        String synopsis,

        @JsonProperty
        List<String> tags,

        @JsonProperty
        List<String> content,

        @JsonProperty
        Boolean active
) {
}
