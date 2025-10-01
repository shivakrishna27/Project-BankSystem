
CREATE TABLE IF NOT EXISTS users (
    form_no VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    father_name VARCHAR(100),
    dob DATE,
    gender VARCHAR(20),
    email VARCHAR(100),
    marital_status VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(50),
    pin_code VARCHAR(10),
    state VARCHAR(50),
    religion VARCHAR(50),
    category VARCHAR(50),
    income VARCHAR(50),
    education VARCHAR(50),
    occupation VARCHAR(50),
    pan VARCHAR(20),
    aadhar VARCHAR(20),
    senior_citizen BOOLEAN DEFAULT FALSE,
    existing_account BOOLEAN DEFAULT FALSE,
    account_type VARCHAR(50),
    card_number VARCHAR(20) NOT NULL,
    pin VARCHAR(10) NOT NULL,
    services VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pin VARCHAR(10) NOT NULL,
    date DATETIME NOT NULL,
    type VARCHAR(20) NOT NULL,
    amount INT NOT NULL
);