package com.lmello.bookstore.service;

import com.lmello.bookstore.dto.tag.TagDTO;
import com.lmello.bookstore.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TagService {
    Tag createTag(TagDTO tagDTO);

    Page<Tag> findAllTags(Pageable pagination);

    Optional<Tag> findByName(String tag);

    Tag updateTag(String id, TagDTO tagDTO);
}
