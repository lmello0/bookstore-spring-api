package com.lmello.bookstore.controller;

import com.lmello.bookstore.dto.tag.TagDTO;
import com.lmello.bookstore.model.Tag;
import com.lmello.bookstore.service.TagService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<?> createTag(@Valid @RequestBody TagDTO tagDTO, UriComponentsBuilder uriComponentsBuilder) {
        Tag tag = tagService.createTag(tagDTO);

        URI uri = uriComponentsBuilder
                .path("/tag/{name}")
                .buildAndExpand(tag.getTagName())
                .toUri();

        TagDTO returnTag = new TagDTO(tag.getId(), tag.getTagName());

        return ResponseEntity.created(uri).body(returnTag);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(tagService.findByName(name));
    }

    @GetMapping
    public ResponseEntity<?> findAllTags(@PageableDefault Pageable pagination) {
        Page<Tag> tags = tagService.findAllTags(pagination);

        return ResponseEntity.ok(tags);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTag(@PathVariable String id, @Valid @RequestBody TagDTO tagDTO) {
        return ResponseEntity.ok(tagService.updateTag(id, tagDTO));
    }
}
