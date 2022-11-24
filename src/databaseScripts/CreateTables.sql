
USE  AgileSprints_SamB;
DROP TABLE Employees;
DROP TABLE Job_Roles;


CREATE TABLE if not exists capability(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	capability_name VARCHAR(15) NOT NULL
);

CREATE TABLE if not exists band(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    band_level TINYINT NOT NULL,
    band_name VARCHAR(20) NOT NULL
);

CREATE TABLE if not exists job_role(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    band_id TINYINT,
    capability_id TINYINT,
	kainos_job_title VARCHAR(30),
	job_family VARCHAR(50),
	job_specification VARCHAR(255),
	job_spec_link VARCHAR(150),
    FOREIGN KEY(band_id)
		REFERENCES band(id),
	FOREIGN KEY(capability_id)
		REFERENCES capability(id)
);


CREATE TABLE if not exists training(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    training_name VARCHAR(80),
    is_mandatory BIT
);

CREATE TABLE if not exists employee(
	id smallint NOT NULL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    national_id_number VARCHAR(15),
    birth_date DATE,
	marital_status CHAR(1),
	gender CHAR(1)
);

CREATE TABLE if not exists training_employee(
	id SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    training_id TINYINT,
    employee_id SMALLINT,
    FOREIGN KEY(training_id)
		REFERENCES training(id),
	FOREIGN KEY(employee_id)
		REFERENCES employee(id)
);

CREATE TABLE if not exists employees_fact(
	id smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	employee_id SMALLINT,
	job_role_id TINYINT,
	hire_date DATE,
	vacation_hours SMALLINT,
	sick_leave_hours SMALLINT,
	current_flag BIT,
	rowgu_id VARCHAR(50),
	salary DECIMAL(7,3),
	location VARCHAR(15),
	FOREIGN KEY(job_role_id)
		REFERENCES job_role(id),
	FOREIGN KEY(employee_id)
		REFERENCES employee(id)
);
