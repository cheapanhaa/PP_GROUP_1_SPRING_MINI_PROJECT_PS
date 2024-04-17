CREATE TABLE users(
    user_id serial primary key,
    email varchar(100),
    password varchar(50),
    profile_image varchar(200)
);

CREATE TABLE otps(
    opt_id serial primary key ,
    opt_code varchar(50),
    issued_at varchar(200),
    expiration varchar(50),
    verity varchar(200),
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE

);

CREATE TABLE categories(
    category_id serial primary key ,
    name varchar(200),
    description varchar(200),
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE

);

CREATE TABLE expenses(
    expense_id serial primary key ,
    amount varchar(200),
    description varchar(200),
    date date,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE ,
    category_id INT REFERENCES categories(category_id) ON DELETE CASCADE
)