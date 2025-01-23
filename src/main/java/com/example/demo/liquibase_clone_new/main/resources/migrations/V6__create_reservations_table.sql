CREATE TABLE IF NOT EXISTS reservations (
                                            id SERIAL PRIMARY KEY NOT NULL,
                                            client_id BIGINT,
                                            instructor_id BIGINT,
                                            pool_id BIGINT,
                                            date_and_time VARCHAR(255),
                                            duration INTEGER,
                                            created_at TIMESTAMP,
                                            updated_at TIMESTAMP
);