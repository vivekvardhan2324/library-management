CREATE DATABASE IF NOT EXISTS librarydb;
USE librarydb;

CREATE TABLE IF NOT EXISTS books (
    id     INT PRIMARY KEY AUTO_INCREMENT,
    bname  VARCHAR(20),
    author VARCHAR(20),
    `type` VARCHAR(20),
    price  FLOAT
);
