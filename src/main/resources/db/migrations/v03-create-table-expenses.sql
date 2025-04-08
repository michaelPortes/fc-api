CREATE TABLE IF NOT EXISTS expenses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    expense BIGINT NOT NULL,
    real_expense_middle_month BIGINT,
    real_expense_final_month BIGINT,
    category_id BIGINT NOT NULL,
    reference_date DATE NOT NULL,
    type TEXT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);