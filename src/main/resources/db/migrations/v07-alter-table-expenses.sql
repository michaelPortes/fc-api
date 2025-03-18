ALTER TABLE fixed_expenses RENAME TO expenses;

ALTER TABLE expenses RENAME COLUMN expected_expense TO expense;

ALTER TABLE expenses ADD COLUMN type VARCHAR(50) NOT NULL DEFAULT 'fixed';