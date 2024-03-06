package com.lmello.bookstore.repository;

import com.lmello.bookstore.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, String> {
    Optional<Tag> findByTagNameIgnoreCase(String s);

    Page<Tag> findAll(Pageable pagination);
}
