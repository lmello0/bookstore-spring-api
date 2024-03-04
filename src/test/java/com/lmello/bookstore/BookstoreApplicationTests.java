package com.lmello.bookstore;

import com.lmello.bookstore.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    private BookController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
