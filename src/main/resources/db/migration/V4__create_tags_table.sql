CREATE TABLE tags (
    id   varchar(128) DEFAULT uuid_generate_v4(),
    tag_name VARCHAR(50),
    PRIMARY KEY (id)
);