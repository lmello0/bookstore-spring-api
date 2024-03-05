package com.lmello.bookstore.service;

import com.lmello.bookstore.dto.paragraph.ParagraphDTO;
import com.lmello.bookstore.model.Book;
import com.lmello.bookstore.model.Paragraph;

import java.util.List;

public interface ParagraphService {
    List<Paragraph> createParagraphs(List<ParagraphDTO> paragraphDTOS, Book book);

    List<Paragraph> updateParagraphs(List<ParagraphDTO> paragraphDTOS, Book book);
}
