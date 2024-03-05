CREATE TABLE books (
    id        VARCHAR(128) NOT NULL,
    title     VARCHAR(256) NOT NULL,
    url       VARCHAR(256) NOT NULL,
    synopsis  VARCHAR(1024) NOT NULL,
    author_id VARCHAR(128) NOT NULL,
    active    BOOLEAN,
    PRIMARY KEY (id)
);