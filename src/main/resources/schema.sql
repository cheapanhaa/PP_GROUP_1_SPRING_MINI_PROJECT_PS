CREATE TABLE users
(
    user_id       SERIAL PRIMARY KEY,
    email         VARCHAR(100),
    password      VARCHAR(200),
    profile_image VARCHAR(200)
);

CREATE TABLE otps
(
    opt_id     SERIAL PRIMARY KEY,
    opt_code   VARCHAR(200),
    issued_at  TIMESTAMP,
    expiration TIMESTAMP,
    verify     BOOLEAN,
    user_id    INT,

    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE

);

CREATE TABLE categories
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(200),
    description VARCHAR(200),
    user_id     INT,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE

);

CREATE TABLE expenses
(
    expense_id  SERIAL PRIMARY KEY,
    amount      FLOAT,
    description VARCHAR(200),
    date        DATE,
    user_id     INT,
    category_id INT,

    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE
);

select user_id
from users
where email = 'tata@gmail.com';