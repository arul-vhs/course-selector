CREATE DATABASE course_selector;

USE course_selector;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    selected_course VARCHAR(50)
);
