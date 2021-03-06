import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class xmlparse {

	private final static String userName = "root";
	private final static String password = "";
	private final static String serverName = "localhost";
	private final static int portNumHme = 3306;
	private final static int portNumHospital = 3306;
//	 private final static String hmeDBName = "healthmessagesexchange2";
	private final static String hmeDBName = "healthmessagesexchange4";
	private final static String hospitalDBName = "hospitalrecords";
	private static String hmeTableName = "messages";
	public static Connection hmeCon = null;
	public static Connection hisCon = null;
	public static String xmlCreationDate1;
	public static MySQLQueries msq;
    public static boolean exit;
    public static boolean patientStatus, doctorStatus, administratorStatus;
    public static String Table;
    public static String Column;
    public static String ColumnValue;
    public static String PatientID;
    public static String choice, ID, privelege, columnChoice, columnValue, view;

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, ParseException, IOException, SAXException, ParserConfigurationException {
		System.out.println("Running main...");

		Statement st = null;
		ResultSet rs = null;

		// String url = "jdbc:mysql://localhost:3306/dbname";
		// String user = "";
		// String password = "";

		Class.forName("com.mysql.jdbc.Driver");
		// con = DriverManager.getConnection(url, user, password);

		hisCon = getConnection(portNumHospital, hospitalDBName);
		hmeCon = getConnection(portNumHme, hmeDBName);
		System.out.println("hisCon=" + hisCon);
		System.out.println("hmeCon=" + hmeCon);

		st = hmeCon.createStatement();
		rs = st.executeQuery("SELECT * FROM " + hmeDBName + "." + hmeTableName + " ORDER BY patientId;");
		parseHmeQuery(rs);
//		
		hmeTableName = "messages2";
		rs = st.executeQuery("SELECT * FROM " + hmeDBName + "." + hmeTableName + " ORDER BY patientId;");
		parseHmeQueryForAdd(rs);
		System.out.println("DONE copying... ");
		
		CLI.enterCLIMode();
	}

	/**
	 * Get database connection
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection(int portNum, String dbName)
			throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":"
				+ portNum + "/" + dbName, connectionProps);

		// String connectionString = "jdbc:mysql://localhost/" + dbName +
		// "?user=" +
		// dbUserName + "&password=" + dbPassword +
		// "&useUnicode=true&characterEncoding=UTF-8";

		return conn;
	}

	/**
	 * Run SQL command - executes update, doesn't return resultset
	 */
	public static boolean executeUpdate(Connection conn, String command)
			throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
//			System.out.println("EXECUTING: " + command);
			stmt.executeUpdate(command); // This will throw a SQLException if it
											// fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) {
				// System.out.println("stmt is null. closing1");
				stmt.close();
			}
		}
	}

	/**
	 * Run SQL query - similar to executeUpdate, but returns resultset
	 */
	public ResultSet executeQuery(Connection conn, String command)
			throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(command); // This will throw a
														// SQLException if it
														// fails
			return rs;
		} finally {
			// This will run whether we throw an exception or not
			if (stmt != null) {
				// System.out.println("stmt is null. closing2");
				// stmt.close();
			}
		}
	}

	// After identifying and
	// separating you need to store the data into the schema you created.
	// While accessing the HealthMessagesExchange ta- ble’s records, update
	// the last accessed time field of each record with the current system
	// date and time. Simultaneously, the patient table’s
	// xmlCreationDatetime
	// needs to be updated with the current date and time

	public static void parseHmeQuery(ResultSet rs) throws SQLException,
			ParseException {
		while (rs.next()) {
			// Retrieve by column name

			String MsgId = rs.getString("MsgId");
			String Last_Accessed = rs.getString("Last_Accessed");
			String patientId = rs.getString("patientId");
			String GivenName = rs.getString("GivenName");
			String FamilyName = rs.getString("FamilyName");
			String BirthTime = rs.getString("BirthTime");
			String providerId = rs.getString("providerId");
			String GuardianNo = rs.getString("GuardianNo"); 
			String Relationship = rs.getString("Relationship");
			String FirstName = rs.getString("FirstName");
			String LastName = rs.getString("LastName");  // corresponds to guardian
			String phone = rs.getString("phone");
			String address = rs.getString("address");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String zip = rs.getString("zip");
			String AuthorId = rs.getString("AuthorId");
			String AuthorTitle = rs.getString("AuthorTitle");
			String AuthorFirstName = rs.getString("AuthorFirstName");
			String AuthorLastName = rs.getString("AuthorLastName");
			String ParticipatingRole = rs.getString("ParticipatingRole");
			String PayerId = rs.getString("PayerId");
			String Name = rs.getString("Name");
			String PolicyHolder = rs.getString("PolicyHolder");
			String PolicyType = rs.getString("PolicyType");
			String Purpose = rs.getString("Purpose");
			String RelativeId = rs.getString("RelativeId");
			String Relation = rs.getString("Relation");
			String age = rs.getString("age");
			String Diagnosis = rs.getString("Diagnosis");
			String AllergyId = rs.getString("Id");
			String Substance = rs.getString("Substance");
			String Reaction = rs.getString("Reaction");
			String Status = rs.getString("Status");
			String LabTestResultId = rs.getString("LabTestResultId");
			String PatientVisitId = rs.getString("PatientVisitId");
			String LabTestPerformedDate = rs.getString("LabTestPerformedDate");
			String LabTestType = rs.getString("LabTestType");
			String TestResultValue = rs.getString("TestResultValue");
			String ReferenceRangeHigh = rs.getString("ReferenceRangeHigh");
			String ReferenceRangeLow = rs.getString("ReferenceRangeLow");
			String PlanId = rs.getString("PlanId");
			String Activity = rs.getString("Activity");
			String ScheduledDate = rs.getString("ScheduledDate");

			if(ScheduledDate != null) {
				String[] splited = ScheduledDate.split("\\s+");
				ScheduledDate = splited[0];
				System.out.println(ScheduledDate);
			}
			if(LabTestPerformedDate != null) {
				String[] splited = LabTestPerformedDate.split("\\s+");
				LabTestPerformedDate = splited[0];
				System.out.println(LabTestPerformedDate);
			}
			if(BirthTime != null) {
				String[] splited = BirthTime.split("\\s+");
				BirthTime = splited[0];
				System.out.println(BirthTime);
			}
			
			String Gender = null; 
			String Suffix = null;

			DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			xmlCreationDate1 = dateFormat.format(date);
			System.out.println("Report Date: " + xmlCreationDate1);


			// add info to proper tables
			addPatientToDB(patientId, GuardianNo, GivenName, FamilyName, Suffix, Gender, BirthTime, 
					providerId, MsgId);
			
			addGuardianToDB(GuardianNo, FirstName, LastName, Relationship, phone, address,
					city, state, zip, patientId);
			
			addAuthorToDB(AuthorId, AuthorTitle, AuthorFirstName,
					AuthorLastName, ParticipatingRole, patientId);
			
			addInsuranceToDB(PayerId, Name, Purpose, PolicyHolder, PolicyType, patientId);
			
			addHistoryToDB(RelativeId, Relation, age, Diagnosis, patientId);
			
			addAllergyToDB(AllergyId, Substance, Reaction, Status, patientId);
			
			addLabReportToDB(LabTestResultId, PatientVisitId,
					LabTestPerformedDate, LabTestType, TestResultValue,
					ReferenceRangeHigh, ReferenceRangeLow, patientId);
			
			addPlanToDB(PlanId, Activity, ScheduledDate, patientId);

		}
	}

	public static void parseHmeQueryForAdd(ResultSet rs) throws SQLException, ParseException {
		
		while (rs.next()) {
			// Retrieve by column name
			String MsgId = rs.getString("MsgId");
			String Last_Accessed = rs.getString("Last_Accessed");
			String patientId = rs.getString("patientId");
			String AllergyId = rs.getString("Id");
			String Substance = rs.getString("Substance");
			String Reaction = rs.getString("Reaction");
			String Status = rs.getString("Status");
			String LabTestResultId = rs.getString("LabTestResultId");
			String PatientVisitId = rs.getString("PatientVisitId");
			String LabTestPerformedDate = rs.getString("LabTestPerformedDate");
			String LabTestType = rs.getString("LabTestType");
			String TestResultValue = rs.getString("TestResultValue");
			String ReferenceRangeHigh = rs.getString("ReferenceRangeHigh");
			String ReferenceRangeLow = rs.getString("ReferenceRangeLow");
			String PlanId = rs.getString("PlanId");
			String Activity = rs.getString("Activity");
			String ScheduledDate = rs.getString("ScheduledDate");
			
			if(ScheduledDate != null) {
				String[] splited = ScheduledDate.split("\\s+");
				ScheduledDate = splited[0];
				System.out.println(ScheduledDate);
			}
			if(LabTestPerformedDate != null) {
				String[] splited = LabTestPerformedDate.split("\\s+");
				LabTestPerformedDate = splited[0];
				System.out.println(LabTestPerformedDate);
			}
			
			DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			xmlCreationDate1 = dateFormat.format(date);
			System.out.println("Report Date: " + xmlCreationDate1);
			
			
			addAllergyToDB(AllergyId, Substance, Reaction, Status, patientId);
			addLabReportToDB(LabTestResultId, PatientVisitId,
					LabTestPerformedDate, LabTestType, TestResultValue,
					ReferenceRangeHigh, ReferenceRangeLow, patientId);
			addPlanToDB(PlanId, Activity, ScheduledDate, patientId);
		}
		
	}
	
	

	
	
	public static void updateXMLTime(String msgID) throws SQLException {
		
//		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
//		Date date = new Date();
//		System.out.println(dateFormat.format(date));
//		xmlCreationDate1 = dateFormat.format(date);
//		System.out.println("Report Date: " + xmlCreationDate1);
		
		String updateXML = "UPDATE " + hmeTableName + " SET Last_Accessed = \""
				+ xmlCreationDate1 + "\" WHERE MsgId = \"" + msgID + "\";";
		System.out.println("updateXML query: " + updateXML);

		executeUpdate(hmeCon, updateXML);
	}

	public static void addPatientToDB(String patientId, String GuardianNo, String GivenName, String FamilyName, 
			String Suffix, String Gender, String BirthTime, String ProviderID, String MsgId)
			throws SQLException {
		
		// Connection conn = getConnection(portNumHospital, hospitalDBName);
		
		if(patientId != null) {
			String update = "INSERT INTO `Patient` (" + "`PatientID`,"
					+ "`GuardianNo`," + "`GivenName`," + "`FamilyName`,"
					+ "`Suffix`," + "`Gender`," + "`BirthTime`," + "`ProviderID`,"
					+ "`xmlHealthCreationDateTime`) VALUES ( \"" + patientId
					+ "\",\"" + GuardianNo + "\",\"" + GivenName
					+ "\",\"" + FamilyName + "\",\"" + Suffix
					+ "\",\"" + Gender + "\",\"" + BirthTime
					+ "\",\"" + ProviderID + "\",\"" + xmlCreationDate1
					+ "\");";
//			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
			updateXMLTime(MsgId);
		}
		return;
	}

	public static void addGuardianToDB(String GuardianNo, String FirstName,
			String LastName, String Relationship, String Phone, String Address, String City,
			String State, String Zip, String PatientID) throws SQLException {

		if(GuardianNo != null && PatientID != null) {
			String update = "INSERT INTO `Has_Guardian` (" + "`GuardianNo`,"
					+ "`FirstName`," + "`LastName`," + "`Relationship`," + "`Phone`," + "`Address`,"
					+ "`City`," + "`State`," + "`Zip`,"
					+ "`PatientID`) VALUES ( \"" + GuardianNo + "\",\"" + FirstName
					+ "\",\"" + LastName + "\",\"" + Relationship + "\",\"" + Phone + "\",\"" + Address
					+ "\",\"" + City + "\",\"" + State + "\",\"" + Zip + "\",\""
					+ PatientID + "\");";
			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
		}
		return;

	}

	public static void addAuthorToDB(String AuthorId, String AuthorTitle,
			String AuthorFirstName, String AuthorLastName,
			String ParticipatingRole, String PatientID) throws SQLException {

		if(AuthorId != null && PatientID != null) {
			String update = "INSERT INTO `Has_Author` (" + "`AuthorID`,"
					+ "`AuthorTitle`," + "`AuthorFirstName`," + "`AuthorLastName`,"
					+ "`ParticipatingRole`," + "`PatientID`) VALUES ( \""
					+ AuthorId + "\",\"" + AuthorTitle + "\",\"" + AuthorFirstName
					+ "\",\"" + AuthorLastName + "\",\"" + ParticipatingRole
					+ "\",\"" + PatientID + "\");";
			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
		}
		return;
	}

	public static void addInsuranceToDB(String payerId, String Name,
			String Purpose, String PolicyHolder, String PolicyType, String PatientID)
			throws SQLException {

		if(payerId != null && PatientID != null) {
			String update = "INSERT INTO `Has_Insurance` (" 
					+ "`PayerID`,"
					+ "`Name`," 
					+ "`Purpose`," 
					+ "`PolicyHolder`,"
					+ "`PolicyType`,"
					+ "`PatientID`) VALUES ( \"" 
					+ payerId 
					+ "\",\"" 
					+ Name
					+ "\",\"" 
					+ Purpose 
					+ "\",\""
					+ PolicyHolder 
					+ "\",\""
					+ PolicyType 
					+ "\",\""
					+ PatientID 
					+ "\");";
			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
		}
		return;
	}

	public static void addHistoryToDB(String RelativeId, String Relation,
			String Age, String Diagnosis, String PatientID) throws SQLException {

		if(RelativeId != null && PatientID != null) {
			String update = "INSERT INTO `Has_History` (" + "`RelativeID`,"
					+ "`Relation`," + "`Age`," + "`Diagnosis`,"
					+ "`PatientID`) VALUES ( \"" + RelativeId + "\",\""
					+ Relation + "\",\"" + Age + "\",\"" + Diagnosis + "\",\""
					+ PatientID + "\");";
			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
		}
		return;
	}

	public static void addAllergyToDB(String AllergyId, String Substance, String Reaction,
			String Status, String PatientID) throws SQLException {

		if(AllergyId != null && PatientID != null) {
			String update = "INSERT INTO `Has_Allergies` (" 
					+ "`AllergyID`,"
					+ "`Substance`,"
					+ "`Reaction`," 
					+ "`Status`," 
					+ "`PatientID`) VALUES ( \""
					+ AllergyId + "\",\""
					+ Substance + "\",\"" 
					+ Reaction + "\",\"" 
					+ Status + "\",\""
					+ PatientID + "\");";
			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
		}
		return;
	}

	public static void addLabReportToDB(String LabTestResultID,
			String PatientVisitID, String LabTestPerformedDate,
			String LabTestType, String TestResultValue,
			String ReferenceRangeHigh, String ReferenceRangeLow,
			String PatientID) throws SQLException {

		if(LabTestResultID != null && PatientVisitID != null && PatientID != null) {
			String update = "INSERT INTO `Has_LabReports` (" + "`LabTestResultID`,"
					+ "`PatientVisitID`," + "`LabTestPerformedDate`,"
					+ "`LabTestType`," + "`TestResultValue`,"
					+ "`ReferenceRangeHigh`," + "`ReferenceRangeLow`,"
					+ "`PatientID`) VALUES ( \"" + LabTestResultID + "\",\""
					+ PatientVisitID + "\",\"" + LabTestPerformedDate + "\",\""
					+ LabTestType + "\",\"" + TestResultValue + "\",\""
					+ ReferenceRangeHigh + "\",\"" + ReferenceRangeLow + "\",\""
					+ PatientID + "\");";
			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
		}
		return;
	}

	public static void addPlanToDB(String PlanId, String Activity, String ScheduledDate, 
			String patientId) throws SQLException {

		if(PlanId != null && patientId != null) {
			String update = "INSERT INTO `Has_Plan` (" + "`PlanId`," + "`Activity`,"
					+ "`ScheduledDate`," + "`PatientID`) VALUES ( \"" + PlanId
					+ "\",\"" + Activity + "\",\"" + ScheduledDate + "\",\"" + patientId + "\");";
			System.out.println("update string: " + update);
	
			executeUpdate(hisCon, update);
		}
		return;
	}

	public static void resetHISDB() throws SQLException {

		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Has_Guardian`;");
		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Has_Author`;");
		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Has_Insurance`;");
		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Has_History`;");
		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Has_Allergies`;");
		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Has_LabReports`;");
		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Has_Plan`;");
		executeUpdate(hisCon, "DROP TABLE IF EXISTS `Patient`;");

		String createTables = "CREATE TABLE Patient (" 
				+ " PatientID CHAR(50),"
				+ " GuardianNo CHAR(50)," 
				+ " GivenName CHAR(50),"
				+ " FamilyName CHAR(50)," 
				+ " Suffix CHAR(50),"
				+ " Gender CHAR(50)," 
				+ " Birthtime CHAR(50),"
				+ " ProviderID CHAR(50),"
				+ " xmlHealthCreationDateTime CHAR(50),"
				+ " UNIQUE (GuardianNo)," 
				+ " PRIMARY KEY (PatientID)" 
				+ " );";
		executeUpdate(hisCon, createTables);

		createTables = "CREATE TABLE Has_Guardian ("
				+ " GuardianNo CHAR(50),"
				+ " GivenName CHAR(50),"
				+ " FamilyName CHAR(50),"
				+ " Phone CHAR(50),"
				+ " Address CHAR(50),"
				+ " City CHAR(50),"
				+ " State CHAR(50),"
				+ " Zip CHAR(50),"
				+ " PatientID CHAR(50),"
				+ " PRIMARY KEY (GuardianNo, PatientID),"
				+ " FOREIGN KEY (GuardianNo) REFERENCES Patient(GuardianNo) ON DELETE CASCADE,"
				+ " FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE"
				+ " );";
		executeUpdate(hisCon, createTables);
		

		createTables = "CREATE TABLE Has_Author("
				+ " AuthorID CHAR(50),"
				+ " AuthorTitle CHAR(50),"
				+ " AuthorFirstName CHAR(50),"
				+ " AuthorLastName CHAR(50),"
				+ " ParticipatingRole CHAR(50),"
				+ " PatientID CHAR(50) NOT NULL,"
				+ " PRIMARY KEY (AuthorID, PatientID, ParticipatingRole),"
				+ " FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE"
				+ " );";
		executeUpdate(hisCon, createTables);

		createTables = "CREATE TABLE Has_Insurance("
				+ " PayerID CHAR(50),"
				+ " Name CHAR(50),"
				+ " Purpose CHAR(50),"
				+ " PolicyType CHAR(50),"
				+ " PatientID CHAR(50) NOT NULL,"
				+ " PRIMARY KEY (PatientID, PayerID),"
				+ " FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE"
				+ " );";
		executeUpdate(hisCon, createTables);

		createTables = "CREATE TABLE Has_History("
				+ " FamilyID CHAR(50),"
				+ " Relationship CHAR(50),"
				+ " Age CHAR(50),"
				+ " Diagnosis CHAR(50),"
				+ " PatientID CHAR(50) NOT NULL,"
				+ " PRIMARY KEY (PatientID, FamilyID, Diagnosis),"
				+ " FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE"
				+ " );";
		executeUpdate(hisCon, createTables);

		createTables = "CREATE TABLE Has_Allergies("
				+ " Substance CHAR(50),"
				+ " Reaction CHAR(50),"
				+ " Status CHAR(50),"
				+ " PatientID CHAR(50) NOT NULL,"
				+ " PRIMARY KEY (PatientID, Substance),"
				+ " FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE"
				+ " );";
		executeUpdate(hisCon, createTables);

		createTables = "CREATE TABLE Has_LabReports("
				+ " LabTestResultID CHAR(50),"
				+ " PatientVisitID CHAR(50),"
				+ " LabTestPerformedDate CHAR(50),"
				+ " LabTestType CHAR(50),"
				+ " TestResultValue CHAR(50),"
				+ " ReferenceRangeHigh CHAR(50),"
				+ " ReferenceRangeLow CHAR(50),"
				+ " PatientID CHAR(50),"
				+ " PRIMARY KEY (PatientID, LabTestResultID, PatientVisitID),"
				+ " FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE"
				+ " );";
		executeUpdate(hisCon, createTables);

		createTables = "CREATE TABLE Has_Plan("
				+ " Activity CHAR(50),"
				+ " ScheduledDate CHAR(50),"
				+ " PatientID CHAR(50),"
				+ " PRIMARY KEY (PatientID, Activity, ScheduledDate),"
				+ " FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE"
				+ " );";

		executeUpdate(hisCon, createTables);
		System.out.println("============RESET TABLES============");

	}

	
	
	
    
	
}




