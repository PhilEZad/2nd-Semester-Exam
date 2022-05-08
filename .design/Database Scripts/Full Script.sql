USE [master]
GO

DROP DATABASE CSe21A_29_SOSU_Sims
GO

CREATE DATABASE CSe21A_29_SOSU_Sims
GO

USE CSe21A_29_SOSU_Sims
GO

CREATE TABLE zipCodes (
    zipCode int UNIQUE NOT NULL,
    cityName NVARCHAR (85),
    PRIMARY KEY (zipCode)
	)

CREATE TABLE schools (
	schoolId INT IDENTITY(1,1) NOT NULL,
	schoolName VARCHAR(50),
	schoolZipCode int,
	PRIMARY KEY (schoolId),
	FOREIGN KEY (schoolZipCode) REFERENCES zipCodes (zipCode)
	)

CREATE TABLE accounts (
    accountId INT IDENTITY(1,1) NOT NULL,
    login VARCHAR(50),
	password VARCHAR(50),
	firstName VARCHAR(25),
	surname VARCHAR(50),
	email VARCHAR(100),
	school int,
	auth int NOT NULL,
	PRIMARY KEY (accountId),
	FOREIGN KEY (school) REFERENCES schools (schoolId) 
	)
	
CREATE TABLE classes (
    classId INT IDENTITY(1,1) NOT NULL,
	className VARCHAR(50),
	teacherID int,
	school int,
	PRIMARY KEY (classId),
	FOREIGN KEY (school) REFERENCES schools (schoolId),
	FOREIGN KEY (teacherID) REFERENCES accounts (accountId) 
	)
	
CREATE TABLE streets (
	streetId int IDENTITY(1,1),
	streetName VARCHAR(255),
	PRIMARY KEY (streetId)
	)
	
CREATE TABLE citizen (
	citizenId int IDENTITY(1,1) NOT NULL,
	cFirstName VARCHAR(50),
	cSurname VARCHAR(50),
	cAge int,
	cStreet int,
	cStreetNumber int,
	cZipCode int,
	cSchool int,
	PRIMARY KEY (citizenId),
	FOREIGN KEY (cStreet) REFERENCES streets (streetId),
	FOREIGN KEY (cZipCode) REFERENCES zipCodes (zipCode),
	FOREIGN KEY (cSchool) REFERENCES schools (schoolId)
	)
	
CREATE TABLE task (
	taskId int IDENTITY(1,1) NOT NULL,
	citizen int,
	report VARCHAR(255),
	school int,
	FOREIGN KEY (school) REFERENCES schools (schoolId)
	)
	
CREATE TABLE taskAssigned(
	task int IDENTITY(1,1) NOT NULL,
	account int,
	PRIMARY KEY (task, account)
	)
	
CREATE TABLE cases(
    caseId int
	)
	
CREATE TABLE generalJournal(
	genJournalId int IDENTITY(1,1) NOT NULL,
	citizen int,
	Primary Key (genJournalId)
	)
	
CREATE TABLE generalCatgories(
	genCatId int IDENTITY(1,1) NOT NULL,
	genSuperCat int,
	genCatName VARCHAR(50),
	PRIMARY KEY (genCatId),
	FOREIGN KEY (genSuperCat) REFERENCES generalCatgories (genCatId)
    )
	
CREATE TABLE generalStatus(
	genId int IDENTITY(1,1) NOT NULL,
	genJournalId int,
	genSuperCat int,
	genSubCat int,
	genDesc1 VARCHAR(255),
	genDesc2 VARCHAR(255),
	genDesc3 VARCHAR(255),
	genStatus int,
	genSchool int,
	PRIMARY KEY (genId),
	FOREIGN KEY (genJournalId) REFERENCES generalJournal (genJournalId),
	FOREIGN KEY (genSuperCat) REFERENCES generalCatgories (genCatId),
	FOREIGN KEY (genSubCat) REFERENCES generalCatgories (genCatId),
	FOREIGN KEY (genSchool) REFERENCES schools (schoolId)
	)
	
CREATE TABLE functionalJournal(
	funJournalId int IDENTITY(1,1) NOT NULL,
	citizen int,
	Primary Key (funJournalId)
	)
	
CREATE TABLE functionalCatgories(
	funCatId int IDENTITY(1,1) NOT NULL,
	funSuperCat int,
	funCatName VARCHAR(50),
	PRIMARY KEY (funCatId),
	FOREIGN KEY (funSuperCat) REFERENCES functionalCatgories (funCatId)
    )
	
CREATE TABLE functionalStatus(
	funId int IDENTITY(1,1) NOT NULL,
	funJournalId int,
	funSuperCat int,
	funSubCat int,
	funDesc1 VARCHAR(255),
	funDesc2 VARCHAR(255),
	funDesc3 VARCHAR(255),
	funStatus int,
	funSatusExpected int,
	funSchool int,
	PRIMARY KEY (funId),
	FOREIGN KEY (funJournalId) REFERENCES functionalJournal (funJournalId),
	FOREIGN KEY (funSuperCat) REFERENCES functionalCatgories (funCatId),
	FOREIGN KEY (funSubCat) REFERENCES functionalCatgories (funCatId),
	FOREIGN KEY (funSchool) REFERENCES schools (schoolId)
	)
	
CREATE TABLE healthJournal(
	heaJournalId int IDENTITY(1,1) NOT NULL,
	citizen int,
	Primary Key (heaJournalId)
	)
	
CREATE TABLE healthCatgories(
	heaCatId int IDENTITY(1,1) NOT NULL,
	heaSuperCat int,
	heaCatName VARCHAR(50),
	PRIMARY KEY (heaCatId),
	FOREIGN KEY (heaSuperCat) REFERENCES healthCatgories (heaCatId)
    )
	
CREATE TABLE healthStatus(
	heaId int IDENTITY(1,1) NOT NULL,
	heaJournalId int,
	heaSuperCat int,
	heaSubCat int,
	heaDesc1 VARCHAR(255),
	heaDesc2 VARCHAR(255),
	heaDesc3 VARCHAR(255),
	heaDtatus int,
	heaSchool int,
	PRIMARY KEY (heaId),
	FOREIGN KEY (heaJournalId) REFERENCES healthJournal (heaJournalId),
	FOREIGN KEY (heaSuperCat) REFERENCES healthCatgories (heaCatId),
	FOREIGN KEY (heaSubCat) REFERENCES healthCatgories (heaCatId),
	FOREIGN KEY (heaSchool) REFERENCES schools (schoolId)
	)