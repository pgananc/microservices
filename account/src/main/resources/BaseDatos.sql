
CREATE TABLE IF NOT EXISTS accounts (
    account_number VARCHAR(255) PRIMARY KEY,
    account_type VARCHAR(255) NOT NULL,
    opening_balance DECIMAL(10, 2) NOT NULL,
    status BOOLEAN DEFAULT TRUE NOT NULL,
    client_id BIGINT NOT NULL,
    client_name VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS transactions (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_type VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

