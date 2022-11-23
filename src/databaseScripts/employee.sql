CREATE DATABASE IF NOT EXISTS AgileSprints_SamB;

USE  AgileSprints_SamB;

CREATE TABLE IF NOT EXISTS Employees(
EmployeeID smallint PRIMARY KEY AUTO_INCREMENT,
FirstName varchar(50),
LastName varchar(50)
);