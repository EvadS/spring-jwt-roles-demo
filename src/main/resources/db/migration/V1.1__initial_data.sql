INSERT INTO role (ROLE_NAME) VALUES ('ROLE_ADMIN');
INSERT INTO role (ROLE_NAME) VALUES ('ROLE_USER');
INSERT INTO role (ROLE_NAME) VALUES ('ROLE_SUPER_ADMIN');



INSERT INTO user (`is_active`, `email`, `is_email_verified`, `password`)
VALUES ( 0b1, 'user@mail.com', b'1' , '$2a$10$qGe7f82yTYlvvmWR5hT/7.913woE6l1is4CWjmtMGLizQr7AUmhdS');

INSERT INTO user (`is_active`, `email`, `is_email_verified`, `password`)
VALUES (0b1, 'admin@mail.com', 0b1, '$2a$10$qGe7f82yTYlvvmWR5hT/7.913woE6l1is4CWjmtMGLizQr7AUmhdS');

INSERT INTO user (`is_active`, `email`, `is_email_verified`, `password`)
VALUES (0b1, 'super-admin@mail.com', 0b1, '$2a$10$qGe7f82yTYlvvmWR5hT/7.913woE6l1is4CWjmtMGLizQr7AUmhdS');
--
INSERT INTO user_authority (`user_id`, `role_id`) VALUES ('1', '5');
INSERT INTO user_authority (`user_id`, `role_id`) VALUES ('2', '4');
INSERT INTO user_authority (`user_id`, `role_id`) VALUES ('3', '6');
