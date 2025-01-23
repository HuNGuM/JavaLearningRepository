CREATE TABLE IF NOT EXISTS pools (
                                     id SERIAL PRIMARY KEY NOT NULL,
                                     lanes INTEGER,
                                     location VARCHAR(255),
                                     name VARCHAR(255),
                                     schedule VARCHAR(255),
                                     employee_id INTEGER,  -- Столбец для связи с employees
                                     CONSTRAINT fk_employee_pools FOREIGN KEY (employee_id) REFERENCES employees(id)
);