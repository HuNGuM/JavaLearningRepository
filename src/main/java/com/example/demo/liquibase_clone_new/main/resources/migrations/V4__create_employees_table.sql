CREATE TABLE IF NOT EXISTS employees (
                                         id SERIAL PRIMARY KEY NOT NULL,
                                         fio VARCHAR(255),
                                         login VARCHAR(255),
                                         password VARCHAR(255),
                                         role_id INTEGER NOT NULL,
                                         CONSTRAINT fk_employee_role FOREIGN KEY (role_id) REFERENCES roles(id)
);
--password for admin is admin (Basic Auth using PostMan)
-- insert into employees (id, fio, login,password, role_id) values (1, 'Admin Admin Admin', 'admin','jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=',3);
