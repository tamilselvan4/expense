
CREATE TABLE Company (
    company_id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(50)
);

CREATE TABLE user_roles (
    role_id INT PRIMARY KEY,
    role_name VARCHAR(20)
);

INSERT IGNORE INTO user_roles (role_id, role_name) VALUES
    (1, 'Administrator'),
    (2, 'Employee');

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT,
    user_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    user_role INT,

    FOREIGN KEY (company_id) REFERENCES Company(company_id),
    FOREIGN KEY (user_role) REFERENCES user_roles(role_id)
);

CREATE TABLE expense_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE
);

INSERT IGNORE INTO expense_categories (category_name) VALUES
    ('Food'),
    ('Transportation'),
    ('Housing'),
    ('Entertainment');

CREATE TABLE state_categories (
    state_id INT PRIMARY KEY,
    state_name VARCHAR(20) NOT NULL UNIQUE
);

INSERT IGNORE INTO state_categories (state_id, state_name) VALUES
    (1, 'Created'),
    (2, 'Requested'),
    (3, 'Accepted'),
    (4, 'Rejected');

CREATE TABLE Expenses (
    expense_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT,
    amount DECIMAL(10,2) NOT NULL,
    date DATE,
    description TEXT,
    file LONGBLOB,
    state_id INT,
    administrator_person_id INT,
    rejected_reason VARCHAR(100),

    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (category_id) REFERENCES expense_categories(category_id),
    FOREIGN KEY (state_id) REFERENCES state_categories(state_id),
    FOREIGN KEY (administrator_person_id) REFERENCES Users(user_id)
);

CREATE TABLE expense_history (
    expense_id INT,
    employee_id INT,
    administrator_id INT,
    state_id INT,
    reason VARCHAR(100),

    FOREIGN KEY (expense_id) REFERENCES Expenses(expense_id),
    FOREIGN KEY (employee_id) REFERENCES Users(user_id),
    FOREIGN KEY (administrator_id) REFERENCES Users(user_id),
    FOREIGN KEY (state_id) REFERENCES state_categories(state_id)
);
