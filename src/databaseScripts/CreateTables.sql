CREATE DATABASE IF NOT EXISTS AgileSprints_SamB;
USE  AgileSprints_SamB;

CREATE TABLE IF NOT EXISTS capability(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	capability_name VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS band(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    band_level TINYINT NOT NULL,
    band_name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS competency(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    competency_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS competency_band(
	id SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    band_id TINYINT,
    competency_id TINYINT,
    subheading VARCHAR(150),
    competency_desc VARCHAR(600),
    FOREIGN KEY(competency_id)
		REFERENCES competency(id),
	FOREIGN KEY(band_id)
		REFERENCES band(id)
);

CREATE TABLE IF NOT EXISTS job_family(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    capability_id TINYINT,
    job_family VARCHAR(50),
	FOREIGN KEY(capability_id)
		REFERENCES capability(id)
);

CREATE TABLE IF NOT EXISTS job_role(
    id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    band_id TINYINT,
    job_family_id TINYINT,
    kainos_job_title VARCHAR(35),
    job_specification VARCHAR(255),
    job_spec_link VARCHAR(500),
    FOREIGN KEY(band_id)
        REFERENCES band(id) ON DELETE CASCADE,
    FOREIGN KEY(job_family_id)
        REFERENCES job_family(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS training(
	id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    training_name VARCHAR(80),
    is_mandatory BIT
);

CREATE TABLE IF NOT EXISTS employee(
	id smallint NOT NULL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    national_id_number VARCHAR(15),
    birth_date DATE,
	marital_status CHAR(1),
	gender CHAR(1)
);

CREATE TABLE IF NOT EXISTS training_employee(
	id SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    training_id TINYINT,
    employee_id SMALLINT,
    FOREIGN KEY(training_id)
		REFERENCES training(id),
	FOREIGN KEY(employee_id)
		REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS employee_fact(
    id smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id SMALLINT,
    job_role_id TINYINT,
    hire_date DATE,
    vacation_hours SMALLINT,
    sick_leave_hours SMALLINT,
    current_flag BIT,
    rowgu_id VARCHAR(50),
    salary DECIMAL(9,2),
    location VARCHAR(15),
    FOREIGN KEY(job_role_id)
        REFERENCES job_role(id) ON DELETE CASCADE,
    FOREIGN KEY(employee_id)
        REFERENCES employee(id) ON DELETE CASCADE
);

