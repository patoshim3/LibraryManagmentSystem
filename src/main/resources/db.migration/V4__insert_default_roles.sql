INSERT INTO t_permission (name) VALUES ('ROLE_USER');
INSERT INTO t_permission (name) VALUES ('ROLE_ADMIN');


INSERT INTO t_user (email, password, username) VALUES ('patima@gmail.com','$2a$12$iUThZSsdxy8I9j2m3PbBce5ySYX4rflV1KELm3rQx8UQ/eMDR3v8C', 'patima');
INSERT INTO t_user (email, password, username) VALUES ('khannuma@gmail.com','$2a$12$iUThZSsdxy8I9j2m3PbBce5ySYX4rflV1KELm3rQx8UQ/eMDR3v8C', 'khannuma');

INSERT INTO t_user_permissions (user_id, permission_id)
VALUES
    (1, 2),
    (2, 1);
