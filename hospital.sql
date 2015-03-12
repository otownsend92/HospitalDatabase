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
       PatientID CHAR(100),
       GuardianNo CHAR(100),
       GivenName CHAR(100) DEFAULT NULL,
       FamilyName CHAR(100) DEFAULT NULL,
       Suffix CHAR(100) DEFAULT NULL,
       Gender CHAR(100) DEFAULT NULL,
       BirthTime CHAR(100) DEFAULT NULL,
       ProviderID CHAR(100) DEFAULT NULL,
       xmlHealthCreationDateTime CHAR(100) DEFAULT NULL,
       UNIQUE (GuardianNo),
       PRIMARY KEY (PatientID)
       );


CREATE TABLE Has_Guardian(
       GuardianNo CHAR(100),
       FirstName CHAR(100) DEFAULT NULL,
       LastName CHAR(100) DEFAULT NULL,
       Relationship CHAR(100) DEFAULT NULL,
       Phone CHAR(100) DEFAULT NULL,
       Address CHAR(100) DEFAULT NULL,
       City CHAR(100) DEFAULT NULL,
       State CHAR(100) DEFAULT NULL,
       Zip CHAR(100) DEFAULT NULL,
       PatientID CHAR(100),
       PRIMARY KEY (GuardianNo, PatientID),
       FOREIGN KEY (GuardianNo) REFERENCES Patient(GuardianNo) ON DELETE CASCADE,
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Author(
       AuthorID CHAR(100),
       AuthorTitle CHAR(100) DEFAULT NULL,
       AuthorFirstName CHAR(100) DEFAULT NULL,
       AuthorLastName CHAR(100) DEFAULT NULL,
       ParticipatingRole CHAR(100),
       PatientID CHAR(100),
       PRIMARY KEY (AuthorID, PatientID, ParticipatingRole),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Insurance(
       PayerID CHAR(100),
       Name CHAR(100) DEFAULT NULL,
       Purpose CHAR(100) DEFAULT NULL,
       PolicyHolder CHAR(100) DEFAULT NULL,
       PolicyType CHAR(100) DEFAULT NULL,
       PatientID CHAR(100),
       PRIMARY KEY (PatientID, PayerID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_History(
       RelativeID CHAR(100),
       Relation CHAR(100) DEFAULT NULL,
       Age CHAR(100) DEFAULT NULL,
       Diagnosis CHAR(100) DEFAULT NULL,
       PatientID CHAR(100),
       PRIMARY KEY (PatientID, RelativeID, Diagnosis),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Allergies(
       AllergyID CHAR(100),
       Substance CHAR(100) DEFAULT NULL,
       Reaction CHAR(100) DEFAULT NULL,
       Status CHAR(100) DEFAULT NULL,
       PatientID CHAR(100),
       PRIMARY KEY (PatientID, AllergyID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_LabReports(
       LabTestResultID CHAR(100),
       PatientVisitID CHAR(100),
       LabTestPerformedDate CHAR(100) DEFAULT NULL,
       LabTestType CHAR(100) DEFAULT NULL,
       TestResultValue CHAR(100) DEFAULT NULL,
       ReferenceRangeHigh CHAR(100) DEFAULT NULL,
       ReferenceRangeLow CHAR(100) DEFAULT NULL,
       PatientID CHAR(100),
       PRIMARY KEY (PatientID, LabTestResultID, PatientVisitID),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );


CREATE TABLE Has_Plan(
       PlanId CHAR(100),
       Activity CHAR(100),
       ScheduledDate CHAR(100),
       PatientID CHAR(100),
       PRIMARY KEY (PatientID, PlanId),
       FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
       );





