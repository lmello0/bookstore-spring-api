package com.lmello.bookstore.model;

import com.lmello.bookstore.dto.paragraph.ParagraphDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "paragraphs")
@Entity(name = "Paragraph")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paragraph {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int paragraphNumber;

    private String paragraph;

    @ManyToOne
    private Book book;

    public Paragraph(ParagraphDTO paragraphDTO, Book book) {
        this.paragraphNumber = paragraphDTO.paragraphNumber();
        this.paragraph = paragraphDTO.paragraph();
        this.book = book;
    }
}
