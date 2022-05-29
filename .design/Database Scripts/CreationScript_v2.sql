
USE master
GO

DROP DATABASE CSe21A_29_SOSU_Sims
GO

CREATE DATABASE CSe21A_29_SOSU_Sims
GO

USE CSe21A_29_SOSU_Sims
GO


/****** Object:  Table Zipcode    Script Date: 17-05-2022 10:32:32 ******/
CREATE TABLE Zipcode (
	Zip int NOT NULL,
	city nvarchar(50) NULL,
 CONSTRAINT PK_Zipcode PRIMARY KEY CLUSTERED (Zip))
 GO


/****** Object:  Table Categories    Script Date: 17-05-2022 10:36:55 ******/

CREATE TABLE Categories (
	CatID int IDENTITY(1,1) NOT NULL,
	FK_ParentCat int NULL,
	catName nvarchar(50) NOT NULL,
 CONSTRAINT PK_Categories PRIMARY KEY CLUSTERED (CatID))
 GO

ALTER TABLE Categories  WITH CHECK ADD  CONSTRAINT FK_Parent_Child_Category FOREIGN KEY(FK_ParentCat)
REFERENCES Categories (CatID)
GO

ALTER TABLE Categories CHECK CONSTRAINT FK_Parent_Child_Category
GO


/****** Object:  Table GeneralInfo    Script Date: 17-05-2022 10:38:52 ******/

CREATE TABLE GeneralInfo(
	InfoID int IDENTITY(1,1) NOT NULL,
	coping nvarchar(50) NULL,
	motivation nvarchar(50) NULL,
	resourses nvarchar(50) NULL,
	roles nvarchar(50) NULL,
	habits nvarchar(50) NULL,
	eduAndJob nvarchar(50) NULL,
	lifestory nvarchar(50) NULL,
	healthInfo nvarchar(50) NULL,
	assistiveDevices nvarchar(50) NULL,
	homelayout nvarchar(50) NULL,
	network nvarchar(50) NULL,
 CONSTRAINT PK_GeneralInfo PRIMARY KEY CLUSTERED (InfoID))
GO


/****** Object:  Table JournalEntry    Script Date: 17-05-2022 10:40:57 ******/

CREATE TABLE JournalEntry(
	EID int IDENTITY(1,1) NOT NULL,
	FK_Category int NOT NULL,
	assessment nvarchar(150) NULL,
	cause nvarchar(150) NULL,
	implications nvarchar(150) NULL,
	currentStatus int NULL,
	expectedStatus int NULL,
	citizenGoals nvarchar(150) NULL,
	notes nvarchar(200) NULL,
 CONSTRAINT PK_JournalEntry PRIMARY KEY CLUSTERED (EID))
 GO

ALTER TABLE JournalEntry  WITH CHECK ADD  CONSTRAINT FK_JournalEntry_Categories FOREIGN KEY(FK_Category)
REFERENCES Categories (CatID)
GO

ALTER TABLE JournalEntry CHECK CONSTRAINT FK_JournalEntry_Categories
GO



/****** Object:  Table School    Script Date: 17-05-2022 10:42:47 ******/

CREATE TABLE School(
	SID int IDENTITY(1,1) NOT NULL,
	schoolName nvarchar(50) NOT NULL,
	FK_Zipcode int NULL,
 CONSTRAINT PK_School PRIMARY KEY CLUSTERED (SID ASC))
GO

ALTER TABLE School  WITH CHECK ADD  CONSTRAINT FK_School_Zipcode FOREIGN KEY(FK_Zipcode)
REFERENCES Zipcode (Zip)
GO

ALTER TABLE School CHECK CONSTRAINT FK_School_Zipcode
GO


/****** Object:  Table Account    Script Date: 17-05-2022 10:38:52 ******/

CREATE TABLE Account(
	AID int IDENTITY(1,1) NOT NULL,
	username nvarchar(30) NOT NULL,
	hashed_pwd nvarchar(80) NOT NULL,
	firstname nvarchar(50) NULL,
	lastname nvarchar(50) NULL,
	email nvarchar(50) NULL,
	FK_AccountSchool int NULL,
	privilegeLevel int NOT NULL,
 CONSTRAINT PK_Account PRIMARY KEY CLUSTERED (AID))
GO

ALTER TABLE Account  WITH CHECK ADD CONSTRAINT FK_Account_School FOREIGN KEY(FK_AccountSchool)
REFERENCES School (SID)
GO

ALTER TABLE Account CHECK CONSTRAINT FK_Account_School
GO


/****** Object:  Table Citizen    Script Date: 17-05-2022 10:44:40 ******/


CREATE TABLE Citizen(
	CID int IDENTITY(1,1) NOT NULL,
	firstName nvarchar(50) NULL,
	lastName nvarchar(50) NULL,
	age int NULL,
	FK_Info int NULL,
	FK_SchoolOwner int NULL,
 CONSTRAINT PK_Citizen PRIMARY KEY CLUSTERED (CID))
GO

ALTER TABLE Citizen  WITH CHECK ADD CONSTRAINT FK_Citizen_GeneralInfo FOREIGN KEY(FK_Info)
REFERENCES GeneralInfo (InfoID)
GO

ALTER TABLE Citizen CHECK CONSTRAINT FK_Citizen_GeneralInfo
GO

ALTER TABLE Citizen  WITH CHECK ADD CONSTRAINT FK_Citizen_School FOREIGN KEY(FK_SchoolOwner)
REFERENCES School (SID)
GO

ALTER TABLE Citizen CHECK CONSTRAINT FK_Citizen_School
GO



/****** Object:  Table [dbo].[Content]    Script Date: 17-05-2022 11:08:09 ******/

CREATE TABLE Content(
	FK_CitizenID int NOT NULL,
	FK_JournalEntry int NOT NULL,
 CONSTRAINT PK_Content PRIMARY KEY CLUSTERED (FK_CitizenID,	FK_JournalEntry))
GO

ALTER TABLE Content  WITH CHECK ADD  CONSTRAINT FK_Content_Citizen FOREIGN KEY(FK_CitizenID)
REFERENCES Citizen (CID)
GO

ALTER TABLE Content CHECK CONSTRAINT FK_Content_Citizen
GO

ALTER TABLE Content  WITH CHECK ADD  CONSTRAINT FK_Content_JournalEntry FOREIGN KEY(FK_JournalEntry)
REFERENCES JournalEntry (EID)
GO

ALTER TABLE Content CHECK CONSTRAINT FK_Content_JournalEntry
GO



/****** Object:  Table Group    Script Date: 17-05-2022 10:46:28 ******/

CREATE TABLE [Group](
	GID int IDENTITY(1,1) NOT NULL,
	groupName nvarchar(50) NULL,
	FK_Citizen int NULL,
 CONSTRAINT PK_Group PRIMARY KEY CLUSTERED (GID))
GO

ALTER TABLE [Group]  WITH CHECK ADD CONSTRAINT FK_Group_Citizen FOREIGN KEY(FK_Citizen)
REFERENCES Citizen (CID)
GO

ALTER TABLE [Group] CHECK CONSTRAINT FK_Group_Citizen
GO


/****** Object:  Table AccountGroup    Script Date: 17-05-2022 10:47:09 ******/

CREATE TABLE AccountGroup(
	FK_GroupID int NOT NULL,
	FK_MemberID int NOT NULL,
 CONSTRAINT PK_AccountGroup PRIMARY KEY CLUSTERED (FK_GroupID, FK_MemberID))
GO

ALTER TABLE AccountGroup  WITH CHECK ADD CONSTRAINT FK_AccountGroup_Account FOREIGN KEY(FK_MemberID)
REFERENCES Account (AID)
GO

ALTER TABLE AccountGroup CHECK CONSTRAINT FK_AccountGroup_Account
GO

ALTER TABLE AccountGroup  WITH CHECK ADD CONSTRAINT FK_AccountGroup_Group FOREIGN KEY(FK_GroupID)
REFERENCES [Group] (GID)
GO

ALTER TABLE AccountGroup CHECK CONSTRAINT FK_AccountGroup_Group
GO

