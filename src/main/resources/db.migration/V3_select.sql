SELECT * FROM authors;
SELECT * FROM books;
SELECT * FROM libraries;
SELECT * FROM library_books;
SELECT * FROM t_user;
SELECT * FROM t_permission;
SELECT * FROM t_user_permissions;

SELECT b.id, b.title, a.name AS author_name
FROM books b
         JOIN authors a ON b.author_id = a.id;


SELECT lb.id, l.name AS library_name, b.title AS book_title, lb.quantity, lb.shelf_code
FROM library_books lb
         JOIN libraries l ON lb.library_id = l.id
         JOIN books b ON lb.book_id = b.id;
