drop database if exists TestDatabase;


CREATE DATABASE TestDatabase;

CREATE USER 'user1'@'localhost'
  IDENTIFIED BY 'pass1';
GRANT ALL PRIVILEGES ON TestDatabase.* TO 'user1'@'localhost';

use TestDatabase;

CREATE TABLE employees (
  id            INT AUTO_INCREMENT UNIQUE PRIMARY KEY,
  date          DATE        NOT NULL,
  email         VARCHAR(30) NOT NULL UNIQUE,
  full_name     VARCHAR(30) NOT NULL,
  department_id INT         NOT NULL REFERENCES departments (id)
);


CREATE TABLE departments (
  id              INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
  department_Name VARCHAR(30)               NOT NULL UNIQUE
);


INSERT INTO departments VALUES (default, "IT department");
--     id=1
INSERT INTO departments VALUES (default, "Happiness department");
--     id=2
INSERT INTO departments VALUES (default, "Science department");
--     id=3


INSERT INTO employees VALUES (default, "1970-12-12", "email1@gmail.com", "Barac Obama", 1);
INSERT INTO employees VALUES (default, "1980-12-12", "email2@gmail.com", "Antonov Anton", 1);
-- 	there are 2 employees in the first department
INSERT INTO employees VALUES (default, "1990-12-12", "email3@gmail.com", "Petrov Petr", 2);
INSERT INTO employees VALUES (default, "2000-12-12", "email4@gmail.com", "Alexandrov Alexandr", 2);
-- 	there are 2 employees in the second department
--  there are no employees in the third department

