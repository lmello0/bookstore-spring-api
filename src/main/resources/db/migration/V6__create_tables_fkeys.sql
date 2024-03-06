ALTER TABLE books
ADD CONSTRAINT books_authors_fk
FOREIGN KEY (author_id)
REFERENCES authors(id);

ALTER TABLE paragraphs
ADD CONSTRAINT paragraphs_books_fk
FOREIGN KEY (book_id)
REFERENCES books(id);

ALTER TABLE tags_books
ADD CONSTRAINT tagsbooks_books_fk
FOREIGN KEY (book_id)
REFERENCES books(id);

ALTER TABLE tags_books
ADD CONSTRAINT tagsbooks_tags_fk
FOREIGN KEY (tag_id)
REFERENCES tags(id);