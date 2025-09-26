CREATE DATABASE feedbackdb;

USE feedbackdb;

CREATE TABLE feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    book_name VARCHAR(100),
    feedback TEXT 
);
