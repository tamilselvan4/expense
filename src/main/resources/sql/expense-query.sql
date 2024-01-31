CREATE DATABASE expense_data;
USE expense_data;

CREATE TABLE company (
    company_id BIGINT UNSIGNED AUTO_INCREMENT,
    company_name VARCHAR(100) UNIQUE NOT NULL,
    
    CONSTRAINT pk_company PRIMARY KEY (company_id)
);

CREATE TABLE user_role (
    role_id TINYINT UNSIGNED,
    role_name VARCHAR(20) NOT NULL,
    
    CONSTRAINT pk_user_role_id PRIMARY KEY (role_id)
);

CREATE TABLE users (
    user_id BIGINT UNSIGNED AUTO_INCREMENT,
    user_company_id BIGINT UNSIGNED NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(50) NOT NULL UNIQUE,
    user_password VARCHAR(50) NOT NULL,
    user_role TINYINT UNSIGNED NOT NULL,
    is_active BOOLEAN DEFAULT true,
    admin_id BIGINT UNSIGNED,
    
    CONSTRAINT pk_user_id PRIMARY KEY (user_id),

    CONSTRAINT fk_user_company FOREIGN KEY (user_company_id) REFERENCES company(company_id),
    CONSTRAINT fk_user_role FOREIGN KEY (user_role) REFERENCES user_role(role_id),
    CONSTRAINT fk_user_admin FOREIGN KEY (admin_id) REFERENCES users(user_id)
);
alter table users modify column user_password VARCHAR(100) NOT NULL;

select * from users;

CREATE TABLE expense_category (
    category_id BIGINT UNSIGNED AUTO_INCREMENT,
    category_name VARCHAR(20) NOT NULL UNIQUE,
    
    CONSTRAINT pk_category_id PRIMARY KEY (category_id)
);

CREATE TABLE expense_category (
    category_id TINYINT UNSIGNED AUTO_INCREMENT,
    category_name VARCHAR(20) NOT NULL UNIQUE,
    category_description TEXT,
    
    CONSTRAINT pk_category_id PRIMARY KEY (category_id)
);

select * from expense_category;
CREATE TABLE expense_status (
    status_id TINYINT,
    status_name VARCHAR(10) NOT NULL UNIQUE,
    
    CONSTRAINT pk_status_id PRIMARY KEY (status_id)
);

CREATE TABLE budget_type (
    budget_type_id TINYINT UNSIGNED AUTO_INCREMENT,
    type_name VARCHAR(20) UNIQUE NOT NULL,
    
    CONSTRAINT pk_budget_type_id PRIMARY KEY (budget_type_id)
);

INSERT INTO budget_type (type_name) VALUES ('company'), ('user');
    
CREATE TABLE budget (
	budget_id BIGINT UNSIGNED AUTO_INCREMENT,
        budget_type_id TINYINT UNSIGNED NOT NULL,
        budget_entity_id BIGINT UNSIGNED,
        budget_category_id BIGINT UNSIGNED default 0,
        budget_start_date DATE,
        budget_end_date DATE,
        budget_amount DECIMAL(10,2) NOT NULL,
        used_budget_amount DECIMAL(10,2) UNSIGNED DEFAULT 0,
        
        CONSTRAINT pk_budget_id PRIMARY KEY (budget_id),
        
        CONSTRAINT fk_budget_category_for_user FOREIGN KEY (budget_category_id) REFERENCES expense_category(category_id)
);

alter table budget modify column used_budget_amount DECIMAL(10,2) UNSIGNED DEFAULT 0;
delete from budget where budget_id = 9;
select * from budget;

CREATE TABLE expense (
    expense_id BIGINT UNSIGNED AUTO_INCREMENT,
    expense_user_id BIGINT UNSIGNED NOT NULL,
    expense_category_id BIGINT UNSIGNED,
    expense_amount DECIMAL(10 , 2) NOT NULL,
    expense_date DATE NOT NULL,
    expense_status_id TINYINT default 1,
    invoice_no INT UNSIGNED,
    expense_description TEXT,
    expense_file LONGBLOB,
    rejected_reason TEXT,
    
    CONSTRAINT pk_expense_id PRIMARY KEY (expense_id),
    
    CONSTRAINT fk_expense_user FOREIGN KEY (expense_user_id) REFERENCES users (user_id),
    CONSTRAINT fk_expense_category FOREIGN KEY (expense_category_id) REFERENCES expense_category (category_id),
    CONSTRAINT fk_expense_status FOREIGN KEY (expense_status_id) REFERENCES expense_status (status_id)
);

select * from expense;

INSERT INTO company (company_id, company_name) VALUE (1, 'abc');

INSERT IGNORE INTO user_role (role_id, role_name) VALUES
    (1, 'Admin'),
    (2, 'Employee');

INSERT IGNORE INTO expense_category (category_name) VALUES
    ('Food'),
    ('Transportation'),
    ('Housing'),
    ('Entertainment');

INSERT IGNORE INTO expense_status (status_id, status_name) VALUES
    (1, 'Created'),
    (2, 'Requested'),
    (3, 'Accepted'),
    (4, 'Rejected');
    




-- CREATE TABLE expense_history (
--     expense_id INT AUTO_INCREMENT PRIMARY KEY,
--     employee_id INT,
--     administrator_id INT,
--     state_id INT,
--     reason VARCHAR(100),
--     FOREIGN KEY (expense_id)
--         REFERENCES Expenses (expense_id),
--     FOREIGN KEY (employee_id)
--         REFERENCES Users (user_id),
--     FOREIGN KEY (administrator_id)
--         REFERENCES Users (user_id),
--     FOREIGN KEY (state_id)
--         REFERENCES state_categories (state_id)
-- );

-- CREATE TABLE user_budget (
-- 	budget_id BIGINT UNSIGNED AUTO_INCREMENT,
--         budget_user_id BIGINT UNSIGNED NOT NULL,
--         budget_category_id BIGINT UNSIGNED NOT NULL,
--         budget_start_date DATE,
--         budget_end_date DATE,
--         budget_amount DECIMAL(10,2) NOT NULL,
--         
--         CONSTRAINT pk_budget_id PRIMARY KEY (budget_id),
--         
--         CONSTRAINT fk_budget_user FOREIGN KEY (budget_user_id) REFERENCES users(user_id),
--         CONSTRAINT fk_budget_category FOREIGN KEY (budget_category_id) REFERENCES expense_category(category_id)
-- );



