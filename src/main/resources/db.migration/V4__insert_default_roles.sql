INSERT INTO t_permission (t_name) VALUES ('ROLE_USER') ON CONFLICT (t_name) DO NOTHING;
INSERT INTO t_permission (t_name) VALUES ('ROLE_ADMIN') ON CONFLICT (t_name) DO NOTHING;