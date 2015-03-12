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
	private final static String hmeDBName = "healthmessagesexchange3";
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

		// conn.close();

		// clear HIS database !!
//		resetHISDB();
		
		System.out.println("\n\n\n\nmessages2\n");

		hmeTableName = "messages2";
		rs = st.executeQuery("SELECT * FROM " + hmeDBName + "." + hmeTableName
				+ " ORDER BY patientId;");
		parseHmeQueryForAdd(rs);

		System.out.println("DONE copying... ");
		
		enterCLIMode();
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
			System.out.println("EXECUTING: " + command);
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

			// System.out.println("======MSGID======: " + MsgId);

			// String suffix = "Mr";
			String Gender = null; // ?????????????????????????????
			String Suffix = null;

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
			
			
			addAllergyToDB(AllergyId, Substance, Reaction, Status, patientId);
			addLabReportToDB(LabTestResultId, PatientVisitId,
					LabTestPerformedDate, LabTestType, TestResultValue,
					ReferenceRangeHigh, ReferenceRangeLow, patientId);
			addPlanToDB(PlanId, Activity, ScheduledDate, patientId);
		}
		
	}
	
	

	
	
	public static void updateXMLTime(String msgID) throws SQLException {

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
			System.out.println("update string: " + update);
	
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

	
	
	public static void enterCLIMode() throws IOException, ClassNotFoundException, SQLException, SAXException, ParserConfigurationException {
		
		msq = new MySQLQueries();
        BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
        exit = false;
        while (!exit)
        {
            System.out.println("HOME MENU:");
            System.out.println("Type 'exit' at anytime to exit");
            System.out.println("What are you?");
            System.out.println("1) Patient");
            System.out.println("2) Doctor");
            System.out.println("3) Administrator");            
            privelege = input.readLine();
            if (privelege.equals("exit")) {exit();}
            while (!privelege.equals("1") && !privelege.equals("2") && !privelege.equals("3"))
            {
                System.out.println(privelege + " is an invalid input. Try again.");
                privelege = input.readLine();
                if (privelege.equals("exit")) {exit();}
            }        
            if (privelege.equals("1"))
            {
                patientStatus = true;
                System.out.println("Please enter your Patient ID");
                ID = input.readLine();
                if (ID.equals("exit")) {exit();}
                while (patientStatus)
                {
                    System.out.println("What would you like to do Patient " + ID + "?");
                    System.out.println("1) View your data");
                    System.out.println("2) Edit patient data");
                    System.out.println("3) Edit guardian data");
                    System.out.println("4) Return to the HOME MENU (if you want to change your patient id)");
                    choice = input.readLine();
                    if (choice.equals("exit")) {exit();}
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"))
                    {
                        System.out.println(choice + " is an invalid input. Try again.");
                        choice = input.readLine();
                        if (choice.equals("exit")) {exit();}
                    }
                    if (choice.equals("1"))
                    {
                        System.out.println("What table would you like to view Patient " + ID + "?");
                        System.out.println("1) Patient");
                        System.out.println("2) Guardian");
                        System.out.println("3) Author");
                        System.out.println("4) Insurance");
                        System.out.println("5) History");
                        System.out.println("6) Allergies");
                        System.out.println("7) Lab Report");
                        System.out.println("8) Plan");
                        System.out.println("9) Return to the HOME MENU (if you want to change your patient id)");
                        view = input.readLine();
                        if (view.equals("exit")) {exit();}
                        while (!view.equals("1") && !view.equals("2") && !view.equals("3") && !view.equals("4") && !view.equals("5") && !view.equals("6") && !view.equals("7") && !view.equals("8") && !view.equals("9"))
                        {
                            System.out.println(view + " is an invalid input. Try again.");
                            view = input.readLine();
                            if (view.equals("exit")) {exit();}
                        }
                        if (view.equals("1")) {Table = "Patient";}
                        else if (view.equals("2")) {Table = "Has_Guardian";}
                        else if (view.equals("3")) {Table = "Has_Author";}
                        else if (view.equals("4")) {Table = "Has_Insurance";}
                        else if (view.equals("5")) {Table = "Has_History";}
                        else if (view.equals("6")) {Table = "Has_Allergies";}
                        else if (view.equals("7")) {Table = "Has_LabReports";}
                        else if (view.equals("8")) {Table = "Has_Plan";}
                        msq.patientView(Table, ID);
                    }
                    else if (choice.equals("2"))
                    {
                        Table = "Patient";
                        System.out.println("What column would you like to edit in the patient table?");
                        System.out.println("1) GuardianNo");
                        System.out.println("2) GivenName");
                        System.out.println("3) FamilyName");
                        System.out.println("4) Suffix");
                        System.out.println("5) Gender");
                        System.out.println("6) Birthtime");
                        System.out.println("7) ProviderID");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3") && !columnChoice.equals("4") && !columnChoice.equals("5") && !columnChoice.equals("6") && !columnChoice.equals("7"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "GuardianNo";
                            System.out.println("What value would you like to insert for your PatientRole?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "GivenName";
                            System.out.println("What value would you like to insert for your GivenName?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("3"))
                        {
                            columnChoice = "FamilyName";
                            System.out.println("What value would you like to insert for your FamilyName?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("4"))
                        {
                            columnChoice = "Suffix";
                            System.out.println("What value would you like to insert for your Suffix?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("5"))
                        {
                            columnChoice = "Gender";
                            System.out.println("What value would you like to insert for your Gender?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("6"))
                        {
                            columnChoice = "Birthtime";
                            System.out.println("What value would you like to insert for your Birthtime?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("7"))
                        {
                            columnChoice = "ProviderID";
                            System.out.println("What value would you like to insert for your ProviderID?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        
                    }
                    else if (choice.equals("3"))
                    {
                        Table = "Has_Guardian";
                        System.out.println("What column would you like to edit in the guardian table for patient ID " + ID + "?");
                        System.out.println("1) FirstName");
                        System.out.println("2) LastName");
                        System.out.println("3) Phone");
                        System.out.println("4) Address");
                        System.out.println("5) City");
                        System.out.println("6) State");
                        System.out.println("7) Zip");
                        System.out.println("8) Relationship");                        
                        System.out.println("9) Nothing. Go back.");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3") && !columnChoice.equals("4") && !columnChoice.equals("5") && !columnChoice.equals("6") && !columnChoice.equals("7") && !columnChoice.equals("8"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "FirstName";
                            System.out.println("What value would you like to insert for 'GivenName'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "LastName";
                            System.out.println("What value would you like to insert for 'FamilyName'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("3"))
                        {
                            columnChoice = "Phone";
                            System.out.println("What value would you like to insert for 'Phone'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("4"))
                        {
                            columnChoice = "Address";
                            System.out.println("What value would you like to insert for 'Address'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("5"))
                        {
                            columnChoice = "City";
                            System.out.println("What value would you like to insert for 'City'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("6"))
                        {
                            columnChoice = "State";
                            System.out.println("What value would you like to insert for 'State'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }                        
                        else if (columnChoice.equals("7"))
                        {
                            columnChoice = "Zip";
                            System.out.println("What value would you like to insert for 'Zip'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("8"))
                        {
                            columnChoice = "Relationship";
                            System.out.println("What value would you like to insert for 'Relationship'?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else
                        {
                            //do nothing. go back to the previous menu.
                        }
                    }
                    else //choice 4
                    {
                        System.out.println("Taking you back to the HOME MENU...");
                        patientStatus = false;
                    }
                }
            }
            else if (privelege.equals("2"))
            {
                doctorStatus = true;
                while (doctorStatus)
                {
                    System.out.println("What would you like to do, doctor?");
                    System.out.println("1) View all patient data");
                    System.out.println("2) Edit plan data");
                    System.out.println("3) Edit allergy data");
                    System.out.println("4) Return to the HOME MENU");
                    choice = input.readLine();
                    if (choice.equals("exit")) {exit();}
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"))
                    {
                        System.out.println(choice + " is an invalid input. Try again.");
                        choice = input.readLine();
                        if (choice.equals("exit")) {exit();}
                    }
                    if (choice.equals("1"))
                    {
                        System.out.println("What table would you like to view for all patients, doctor?");
                        System.out.println("1) Patient");
                        System.out.println("2) Guardian");
                        System.out.println("3) Author");
                        System.out.println("4) Insurance");
                        System.out.println("5) History");
                        System.out.println("6) Allergies");
                        System.out.println("7) Lab Report");
                        System.out.println("8) Plan");
                        view = input.readLine();
                        if (view.equals("exit")) {exit();}
                        while (!view.equals("1") && !view.equals("2") && !view.equals("3") && !view.equals("4") && !view.equals("5") && !view.equals("6") && !view.equals("7") && !view.equals("8") && !view.equals("9"))
                        {
                            System.out.println(view + " is an invalid input. Try again.");
                            view = input.readLine();
                            if (view.equals("exit")) {exit();}
                        }
                        if (view.equals("1")) {Table = "Patient";}
                        else if (view.equals("2")) {Table = "Has_Guardian";}
                        else if (view.equals("3")) {Table = "Has_Author";}
                        else if (view.equals("4")) {Table = "Has_Insurance";}
                        else if (view.equals("5")) {Table = "Has_History";}
                        else if (view.equals("6")) {Table = "Has_Allergies";}
                        else if (view.equals("7")) {Table = "Has_LabReports";}
                        else if (view.equals("8")) {Table = "Has_Plan";}
                        msq.doctorView(Table);
                    }
                    else if (choice.equals("2"))
                    {
                        Table = "Has_Plan";
                        System.out.println("What Patient ID in the Plan Table would you like to edit?");
                        ID = input.readLine();
                        if (ID.equals("exit")) {exit();}
                        System.out.println("What would you like to edit in the Plan Table for Patient ID " + ID + ", doctor?");
                        System.out.println("1) Activity");
                        System.out.println("2) ScheduledDate");
                        System.out.println("3) Nothing. Go back.");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "Activity";
                            System.out.println("What value would you like to insert for the Activity?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "ScheduledDate";
                            System.out.println("What value would you like to insert for the ScheduledDate?");
                            columnValue = input.readLine();
                            if (columnValue.equals("exit")) {exit();}
                            msq.updateTable(Table, columnChoice, columnValue, ID);
                        }
                        else
                        {
                            //Do nothing and go back to the previous menu
                        }
                    }
                    else if (choice.equals("3")) //Edit allergy table 
                    {
                        Table = "Has_Allergies";
                        System.out.println("What Patient ID in the Allergies Table would you like to edit?");
                        ID = input.readLine();
                        if (ID.equals("exit")) {exit();}
                        System.out.println("What would you like to edit in the Allergies Table for Patient ID " + ID + ", doctor?");
                        System.out.println("1) Substance");
                        System.out.println("2) Reaction");
                        System.out.println("3) Status");
                        System.out.println("4) Nothing. Go back.");
                        columnChoice = input.readLine();
                        if (columnChoice.equals("exit")) {exit();}
                        while (!columnChoice.equals("1") && !columnChoice.equals("2") && !columnChoice.equals("3") && !columnChoice.equals("4"))
                        {
                            System.out.println(columnChoice + " is an invalid input. Try again.");
                            columnChoice = input.readLine();
                            if (columnChoice.equals("exit")) {exit();}
                        }
                        if (columnChoice.equals("1"))
                        {
                            columnChoice = "Substance";
                            System.out.println("What value would you like to insert for the Substance?");
                        }
                        else if (columnChoice.equals("2"))
                        {
                            columnChoice = "Reaction";
                            System.out.println("What value would you like to insert for the Reaction?");
                        }
                        else if (columnChoice.equals("3"))
                        {
                            columnChoice = "Status";
                            System.out.println("What value would you like to insert for the Status?");
                        }
                        else
                        {
                            //Do nothing and go back to the previous menu
                        }
                        columnValue = input.readLine();
                        if (columnValue.equals("exit")) {exit();}
                        msq.updateTable(Table, columnChoice, columnValue, ID);
                    }
                    else //return to home menu
                    {
                        System.out.println("Taking you back to the HOME MENU...");
                        doctorStatus = false;
                    }
                }
            }
            else //administrator
            {
                administratorStatus = true;
                while (administratorStatus)
                {
                    System.out.println("What would you like to do?");
                    System.out.println("1) View number of patients for each type of allergy (substance)");
                    System.out.println("2) List the patients who have more than one allergy");
                    System.out.println("3) List the patients who have a plan for surgery today");
                    System.out.println("4) Identify authors with more than one patient");
                    System.out.println("5) Return to the HOME MENU");

                    choice = input.readLine();
                    if (choice.equals("exit")) {exit();} 
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5"))
                    {
                        System.out.println(choice + " is an invalid input. Try again.");
                        choice = input.readLine();
                        if (choice.equals("exit")) {exit();}
                    }
                    if (choice.equals("1"))
                    {
                        //View number of patients for each type of allergy (substance)
                        msq.Administrator_numPatientsPerAllergy();
                    }
                    else if (choice.equals("2"))
                    {
                        //List the patients who have more than one allergy
                        msq.Administrator_patientsWithAllergies();
                    }
                    else if (choice.equals("3"))
                    {
                        //List the patients who have a plan for surgery today
                        msq.Administrator_patientsWithPlans();
                    }
                    else if (choice.equals("4"))
                    {
                        //List the authors with more than one patient
                        msq.Administrator_authorsWithPatients();
                    }
                    else //return to home menu
                    {
                        System.out.println("Taking you back to the HOME MENU...");
                        administratorStatus = false;
                    }
                }
            }
        }

    }
	
	public static void exit()
    {
        System.out.println("Goodbye.");
        System.exit(0); 
    }
    
	
}




