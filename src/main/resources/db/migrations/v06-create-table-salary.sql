CREATE TABLE IF NOT EXISTS salary (
    id SERIAL PRIMARY KEY,
    salary BIGINT NOT NULL,
    reference_date DATE NOT NULL
);