package com.lmello.bookstore.controller;

import com.lmello.bookstore.dto.BookDTO;
import com.lmello.bookstore.model.Book;
import com.lmello.bookstore.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<BookDTO> bookDTOJacksonTester;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Should return HTTP 400 when body is invalid")
    void createBook400() throws Exception {
        MockHttpServletResponse response = mvc
                .perform(post("/books"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return HTTP 201 when body is valid and book isn't created yet")
    void createBook201() throws Exception {
        BookDTO newBook = new BookDTO(
                null,
                "test",
                "test",
                "test",
                "test",
                Arrays.asList("tag1", "tag2"),
                Arrays.asList("paragraph1", "paragraph2"),
                null
        );

        BookDTO expectedBookDTO = new BookDTO(
                "test",
                "test",
                "test",
                "test",
                "test",
                Arrays.asList("tag1", "tag2"),
                Arrays.asList("paragraph1", "paragraph2"),
                true
        );

        Book expectedBook = new Book(
                "test",
                "test",
                "test",
                "test",
                "test",
                Arrays.asList("tag1", "tag2"),
                Arrays.asList("paragraph1", "paragraph2"),
                true
        );

        String expectedJson = bookDTOJacksonTester.write(expectedBookDTO).getJson();

        when(bookService.createBook(any())).thenReturn(expectedBook);

        MockHttpServletResponse response = mvc
                .perform(
                        post("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookDTOJacksonTester.write(newBook).getJson())
                ).andReturn()
                .getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}
