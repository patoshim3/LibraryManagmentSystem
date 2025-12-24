CREATE TABLE authors(
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   biography TEXT
);

CREATE TABLE books(
   id BIGSERIAL PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   isbn VARCHAR(50),
   publication_year INT,
   author_id BIGINT NOT NULL,
   CONSTRAINT fk_books_authors FOREIGN KEY (author_id) REFERENCES authors(id)
);