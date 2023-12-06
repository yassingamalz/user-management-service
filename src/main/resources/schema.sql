-- user table
CREATE TABLE IF NOT EXISTS _user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    email VARCHAR(255)
);

-- role table
CREATE TABLE IF NOT EXISTS role (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE
);

-- user_role table
CREATE TABLE IF NOT EXISTS user_role (
    user_role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES _user(user_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- permission table
CREATE TABLE IF NOT EXISTS permission (
    permission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    permission_name VARCHAR(255) UNIQUE
);

-- role_permission table
CREATE TABLE IF NOT EXISTS role_permission (
    role_permission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT,
    permission_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES role(role_id),
    FOREIGN KEY (permission_id) REFERENCES permission(permission_id)
);
