package com.lmello.bookstore.service.implementation;

import com.lmello.bookstore.dto.tag.TagDTO;
import com.lmello.bookstore.exception.DuplicateEntryException;
import com.lmello.bookstore.exception.NotFoundException;
import com.lmello.bookstore.model.Tag;
import com.lmello.bookstore.repository.TagRepository;
import com.lmello.bookstore.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Tag createTag(TagDTO tagDTO) {
        Optional<Tag> tag = tagRepository.findByTagNameIgnoreCase(tagDTO.tagName());

        if (tag.isPresent()) {
            throw new DuplicateEntryException("\"" + tag.get().getTagName() + "\" already exists");
        }

        return tagRepository.save(new Tag(tagDTO));
    }

    @Override
    public Page<Tag> findAllTags(Pageable pagination) {
        return tagRepository.findAll(pagination);
    }

    @Override
    public Optional<Tag> findByName(String tagName) {
        return tagRepository.findByTagNameIgnoreCase(tagName);
    }

    @Override
    @Transactional
    public Tag updateTag(String id, TagDTO tagDTO) {
        Optional<Tag> tag = tagRepository.findById(id);

        if (tag.isEmpty()) {
            throw new NotFoundException("\"" + id + "\" not found");
        }

        BeanUtils.copyProperties(tagDTO, tag.get(), "id");

        return tagRepository.save(tag.get());
    }
}
