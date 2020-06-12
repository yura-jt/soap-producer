CREATE DATABASE IF NOT EXISTS soap_producer
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE soap_producer;

DROP TABLE IF EXISTS students;

CREATE TABLE students
(
    id           BIGINT(20)   NOT NULL AUTO_INCREMENT,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(50)  NOT NULL,
    email        VARCHAR(254) NOT NULL,
    age          INT(3)       NOT NULL,
    group_number INT(3)       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT users_email_AK UNIQUE (email)
);
