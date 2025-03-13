CREATE TABLE IF NOT EXISTS launch (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    date_maturity DATE NOT NULL,
    date_payment DATE,
    cost NUMERIC(10, 2) NOT NULL,
    observation VARCHAR(50),
    type VARCHAR(20) NOT NULL,
    category_id BIGINT NOT NULL,
    users_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (users_id) REFERENCES users(id)
);
