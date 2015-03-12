CREATE DATABASE  IF NOT EXISTS `hospitalrecords` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hospitalrecords`;

DROP TABLE IF EXISTS `Has_Guardian`;
DROP TABLE IF EXISTS `Has_Author`;
DROP TABLE IF EXISTS `Has_Insurance`;
DROP TABLE IF EXISTS `Has_History`;
DROP TABLE IF EXISTS `Has_Allergies`;
DROP TABLE IF EXISTS `Has_LabReports`;
DROP TABLE IF EXISTS `Has_Plan`;
DROP TABLE IF EXISTS `Patient`;


CREATE TABLE Patient (
       PatientID CHAR(50),
       GuardianNo CHAR(50),
       GivenName CHAR(50),
       FamilyName CHAR(50),
       Suffix CHAR(50),
       Gender CHAR(50),
       Birthtime CHAR(50),
       ProviderID CHAR(50),
       xmlHealthCreationDateTime CHAR(50),
       UNIQUE (GuardianNo),
       PRIMARY KEY (PatientID)
       );


CREATE TABLE Has_Guardian(
	GuardianNo CHAR(50),
	GivenName CHAR(50),
	FamilyName CHAR(50),
	Phone CHAR(50),
	Address CHAR(50),
	City CHAR(50),
	State CHAR(50),
	Zip CHAR(50),
	PatientID CHAR(50),
	PRIMARY KEY (GuardianNo, PatientID),
	FOREIGN KEY (GuardianNo) REFERENCES Patient(GuardianNo) ON DELETE CASCADE,
	FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
	);


CREATE TABLE Has_Author(
       AuthorID CHAR(50),
       AuthorTitle CHAR(50),
       AuthorFirstName CHAR(50),
       AuthorLastName CHAR(50),
       ParticipatingRole CHAR(50),
       PatientID CHAR(50) NOT NULL,
       PRIMARY KEY (AuthorID, PatientID, ParticipatingRole),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Insurance(
       PayerID CHAR(50),
       Name CHAR(50),
       Purpose CHAR(50),
       PolicyType CHAR(50),
       PatientID CHAR(50) NOT NULL,
       PRIMARY KEY (PatientID, PayerID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_History(
       FamilyID CHAR(50),
       Relationship CHAR(50),
       Age CHAR(50),
       Diagnosis CHAR(50),
       PatientID CHAR(50) NOT NULL,
       PRIMARY KEY (PatientID, FamilyID, Diagnosis),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Allergies(
       Substance CHAR(50),
       Reaction CHAR(50),
       Status CHAR(50),
       PatientID CHAR(50) NOT NULL,
       PRIMARY KEY (PatientID, Substance),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_LabReports(
       LabTestResultID CHAR(50),
       PatientVisitID CHAR(50),
       LabTestPerformedDate CHAR(50),
       LabTestType CHAR(50),
       TestResultValue CHAR(50),
       ReferenceRangeHigh CHAR(50),
       ReferenceRangeLow CHAR(50),
       PatientID CHAR(50),
       PRIMARY KEY (PatientID, LabTestResultID, PatientVisitID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Plan(
       Activity CHAR(50),
       ScheduledDate CHAR(50),
       PatientID CHAR(50),
       PRIMARY KEY (PatientID, Activity, ScheduledDate),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );
