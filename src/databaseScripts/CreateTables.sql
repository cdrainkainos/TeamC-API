
USE  AgileSprints_SamB;
DROP TABLE Employees;
DROP TABLE Job_Roles;


CREATE TABLE if not exists Capabilities(
	ID TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	CapabilityName varchar(15)
);

CREATE TABLE if not exists Bands(
	ID TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BandLevel TINYINT,
    BandName varchar(20)
);

CREATE TABLE if not exists JobRoles(
	ID TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BandID TINYINT,
    CapabilityID TINYINT,
	KainosJobTitle varchar(30),
	JobFamily varchar(50),
	JobSpecification varchar(255),
	JobSpecLink varchar(150),
    FOREIGN KEY(BandID)
		REFERENCES Bands(ID),
	FOREIGN KEY(CapabilityID)
		REFERENCES Capabilities(ID)
);


CREATE TABLE if not exists Trainings(
	ID TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TrainingName varchar(80),
    IsObligatory BIT -- Flag if the training is obligatory
);

CREATE TABLE if not exists Employees(
	ID smallint NOT NULL PRIMARY KEY,
    FirstName varchar(50),
    LastName varchar(50),
    NationalIDNumber varchar(15),
    BirthDate DATE,
	MaritalStatus char(1),
	Gender char(1)
);

CREATE TABLE if not exists Training_Employee(
	ID SMALLINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TrainingID TINYINT,
    EmployeeID SMALLINT, -- Employee ID
    FOREIGN KEY(TrainingID)
		REFERENCES Trainings(ID),
	FOREIGN KEY(EmployeeID)
		REFERENCES Employees(ID)
);

CREATE TABLE if not exists Employees_fact(
	ID smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	EmployeeID smallint, -- Employee ID
	JobRoleID TINYINT,
	HireDate DATE,
	VacationHours SMALLINT,
	SickLeaveHours SMALLINT,
	CurrentFlag BIT,
	Rowguid varchar(50),
	Salary DECIMAL(7,3),
	Location varchar(15),
	FOREIGN KEY(JobRoleID)
		REFERENCES JobRoles(ID),
	FOREIGN KEY(EmployeeID)
		REFERENCES Employees(ID)
);

