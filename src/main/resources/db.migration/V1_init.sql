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

CREATE TABLE libraries (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   address TEXT
);


CREATE TABLE library_books (
    id BIGSERIAL PRIMARY KEY,
    library_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    quantity INT,
    shelf_code VARCHAR(50),
    CONSTRAINT fk_library_books_library FOREIGN KEY (library_id) REFERENCES libraries(id),
    CONSTRAINT fk_library_books_book FOREIGN KEY (book_id) REFERENCES books(id)
);


CREATE TABLE t_user (
     user_id BIGSERIAL PRIMARY KEY,
     t_email VARCHAR(255) NOT NULL UNIQUE,
     t_password VARCHAR(255) NOT NULL
);

CREATE TABLE t_permission (
     permission_id BIGSERIAL PRIMARY KEY,
     t_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE t_user_permissions (
     user_id BIGINT NOT NULL,
     permission_id BIGINT NOT NULL,
     CONSTRAINT fk_user_permissions_user  FOREIGN KEY (user_id) REFERENCES t_user(user_id) ON DELETE CASCADE,
     CONSTRAINT fk_user_permissions_permission FOREIGN KEY (permission_id) REFERENCES t_permission(permission_id) ON DELETE CASCADE, PRIMARY KEY (user_id, permission_id)
);