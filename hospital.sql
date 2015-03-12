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
       GivenName CHAR(50) DEFAULT NULL,
       FamilyName CHAR(50) DEFAULT NULL,
       Suffix CHAR(50) DEFAULT NULL,
       Gender CHAR(50) DEFAULT NULL,
       BirthTime CHAR(50) DEFAULT NULL,
       ProviderID CHAR(50) DEFAULT NULL,
       xmlHealthCreationDateTime CHAR(50) DEFAULT NULL,
       UNIQUE (GuardianNo),
       PRIMARY KEY (PatientID)
       );


CREATE TABLE Has_Guardian(
       GuardianNo CHAR(50),
       FirstName CHAR(50) DEFAULT NULL,
       LastName CHAR(50) DEFAULT NULL,
       Relationship CHAR(50) DEFAULT NULL,
       Phone CHAR(50) DEFAULT NULL,
       Address CHAR(50) DEFAULT NULL,
       City CHAR(50) DEFAULT NULL,
       State CHAR(50) DEFAULT NULL,
       Zip CHAR(50) DEFAULT NULL,
       PatientID CHAR(50),
       PRIMARY KEY (GuardianNo, PatientID),
       FOREIGN KEY (GuardianNo) REFERENCES Patient(GuardianNo) ON DELETE CASCADE,
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Author(
       AuthorID CHAR(50),
       AuthorTitle CHAR(50) DEFAULT NULL,
       AuthorFirstName CHAR(50) DEFAULT NULL,
       AuthorLastName CHAR(50) DEFAULT NULL,
       ParticipatingRole CHAR(50),
       PatientID CHAR(50),
       PRIMARY KEY (AuthorID, PatientID, ParticipatingRole),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Insurance(
       PayerID CHAR(50),
       Name CHAR(50) DEFAULT NULL,
       Purpose CHAR(50) DEFAULT NULL,
       PolicyHolder CHAR(50) DEFAULT NULL,
       PolicyType CHAR(50) DEFAULT NULL,
       PatientID CHAR(50),
       PRIMARY KEY (PatientID, PayerID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_History(
       RelativeID CHAR(50),
       Relation CHAR(50) DEFAULT NULL,
       Age CHAR(50) DEFAULT NULL,
       Diagnosis CHAR(50) DEFAULT NULL,
       PatientID CHAR(50),
       PRIMARY KEY (PatientID, RelativeID, Diagnosis),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Allergies(
       AllergyID CHAR(50),
       Substance CHAR(50) DEFAULT NULL,
       Reaction CHAR(50) DEFAULT NULL,
       Status CHAR(50) DEFAULT NULL,
       PatientID CHAR(50),
       PRIMARY KEY (PatientID, AllergyID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_LabReports(
       LabTestResultID CHAR(50),
       PatientVisitID CHAR(50),
       LabTestPerformedDate CHAR(50) DEFAULT NULL,
       LabTestType CHAR(50) DEFAULT NULL,
       TestResultValue CHAR(50) DEFAULT NULL,
       ReferenceRangeHigh CHAR(50) DEFAULT NULL,
       ReferenceRangeLow CHAR(50) DEFAULT NULL,
       PatientID CHAR(50),
       PRIMARY KEY (PatientID, LabTestResultID, PatientVisitID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Plan(
       PlanId CHAR(50),
       Activity CHAR(50),
       ScheduledDate CHAR(50),
       PatientID CHAR(50),
       PRIMARY KEY (PatientID, PlanId),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );





