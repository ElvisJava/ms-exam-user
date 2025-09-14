-- Script SQL para crear tablas (H2 compatible)
CREATE TABLE users (
id VARCHAR(36) PRIMARY KEY,
name VARCHAR(255),
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255),
created TIMESTAMP,
modified TIMESTAMP,
last_login TIMESTAMP,
token VARCHAR(512),
is_active BOOLEAN
);
CREATE TABLE phones (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
number VARCHAR(50),
citycode VARCHAR(50),
contrycode VARCHAR(50),
user_id VARCHAR(36)
);
