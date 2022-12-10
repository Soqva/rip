INSERT INTO client (username, password, banned)
VALUES ('user', '{bcrypt}$2a$10$bmKcha.WtPVvDvYXsLOGL.ZCPIu.OFGSSUtNTyLBeHEkrQO81UuSO', FALSE), -- пароль: user
       ('admin', '{bcrypt}$2a$10$60qitxcNUJFRrEK3Ykyv8uoKGqgHM2wxXb5exzCMPypne5qfPiJR2', FALSE); -- пароль: admin

INSERT INTO role (code, name, description)
VALUES ('USER', 'Пользователь', 'Простой пользователь приложения'),
       ('ADMIN', 'Администратор', 'Администратор пользователь приложения');

INSERT INTO client_role (client_id, role_code)
VALUES (1, 'USER'),
       (2, 'ADMIN');
