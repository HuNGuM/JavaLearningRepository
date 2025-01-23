CREATE TABLE IF NOT EXISTS clients (
                                       id SERIAL PRIMARY KEY NOT NULL,
                                       fio VARCHAR(255),
                                       login VARCHAR(255),
                                       password VARCHAR(255),
                                       external_id UUID NOT NULL UNIQUE
);