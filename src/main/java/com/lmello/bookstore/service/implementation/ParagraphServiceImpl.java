package com.lmello.bookstore.service.implementation;

import com.lmello.bookstore.dto.paragraph.ParagraphDTO;
import com.lmello.bookstore.model.Book;
import com.lmello.bookstore.model.Paragraph;
import com.lmello.bookstore.repository.ParagraphRepository;
import com.lmello.bookstore.service.ParagraphService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParagraphServiceImpl implements ParagraphService {

    @PersistenceContext
    EntityManager entityManager;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int BATCH_SIZE;

    private final ParagraphRepository paragraphRepository;

    public ParagraphServiceImpl(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    @Override
    @Transactional
    public List<Paragraph> createParagraphs(List<ParagraphDTO> paragraphDTOS, Book book) {
        List<Paragraph> returnParagraphs = new ArrayList<>();

        for (int i = 0; i < paragraphDTOS.size(); i++) {
            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }

            Paragraph paragraph = new Paragraph(paragraphDTOS.get(i), book);
            entityManager.persist(paragraph);

            returnParagraphs.add(paragraph);
        }

        return returnParagraphs;
    }

    @Override
    @Transactional
    public List<Paragraph> updateParagraphs(List<ParagraphDTO> paragraphDTOS, Book book) {
        paragraphRepository.deleteAllByBook(book);

        return createParagraphs(paragraphDTOS, book);
    }
}
