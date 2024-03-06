CREATE TABLE authors (
    id   varchar(128) DEFAULT uuid_generate_v4(),
    name VARCHAR(128),
    PRIMARY KEY (id)
);