CREATE TABLE teachers (
    id INT IDENTITY(1,1) NOT NULL,
    teacherLogin VARCHAR(50),
	teacherPassword VARCHAR(50),
	teacherFirstName VARCHAR(25),
	teacherSurname VARCHAR(50),
	teacherEmail VARCHAR(100),
	salt VARCHAR(50),
	school int,
	PRIMARY KEY (id),
	FOREIGN KEY (school) REFERENCES school (SID)
	)