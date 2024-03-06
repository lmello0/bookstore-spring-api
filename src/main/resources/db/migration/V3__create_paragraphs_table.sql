CREATE TABLE paragraphs (
    id   varchar(128) DEFAULT uuid_generate_v4(),
    book_id          varchar(128) NOT NULL,
    paragraph_number INTEGER,
    paragraph        TEXT,
    PRIMARY KEY (id)
);