CREATE TABLE IF NOT EXISTS roles (
                                     id SERIAL PRIMARY KEY NOT NULL,
                                     name VARCHAR(255)
);

-- Вставка значений
INSERT INTO roles (name) VALUES ('INSTRUCTOR');
INSERT INTO roles (name) VALUES ('MANAGER');
INSERT INTO roles (name) VALUES ('ADMIN');