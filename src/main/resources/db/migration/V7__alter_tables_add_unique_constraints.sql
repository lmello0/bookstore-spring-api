ALTER TABLE authors
ADD CONSTRAINT unique_name_authors UNIQUE (name);

ALTER TABLE books
ADD CONSTRAINT unique_title_author_books UNIQUE (title, author_id);

ALTER TABLE paragraphs
ADD CONSTRAINT unique_paragraph_number_paragraph_book_paragraphs UNIQUE (paragraph_number, paragraph, book_id);

ALTER TABLE tags
ADD CONSTRAINT unique_tag_name_tags UNIQUE (tag_name);

ALTER TABLE tags_books
ADD CONSTRAINT unique_book_tag UNIQUE (book_id, tag_id);