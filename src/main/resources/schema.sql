CREATE TABLE transactions
(
    transaction_id INT NOT NULL
    AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR
    (255) NOT NULL,
    transaction_type VARCHAR
    (255) NOT NULL,
    amount DOUBLE NOT NULL,
    interest_amount DOUBLE NOT NULL,
    transaction_date_time TIMESTAMP NOT NULL,
    community_id INT NOT NULL
);
