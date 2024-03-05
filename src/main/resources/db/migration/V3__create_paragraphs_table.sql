CREATE TABLE paragraphs (
    id               VARCHAR(128) NOT NULL,
    book_id          VARCHAR(128) NOT NULL,
    paragraph_number INTEGER,
    paragraph        TEXT,
    PRIMARY KEY (id)
);