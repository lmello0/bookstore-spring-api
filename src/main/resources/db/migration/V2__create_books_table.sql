CREATE TABLE books (
    id   varchar(128) DEFAULT uuid_generate_v4(),
    title     VARCHAR(256) NOT NULL,
    url       VARCHAR(256) NOT NULL,
    synopsis  VARCHAR(1024) NOT NULL,
    author_id varchar(128) NOT NULL,
    active    BOOLEAN,
    PRIMARY KEY (id)
);