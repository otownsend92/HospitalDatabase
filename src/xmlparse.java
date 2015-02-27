import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class xmlparse {

	private final static String userName = "root";
	private final static String password = "";
	private final static String serverName = "localhost";
	private final static int portNumHme = 3306;
	private final static int portNumHospital = 3307;
	private final static String hmeDBName = "healthmessagesexchange2";
	private final static String hospitalDBName = "hospitalrecords";
	private final static String hmeTableName = "messages";

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		System.out.println("Running main...");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		// String url = "jdbc:mysql://localhost:3306/dbname";
		// String user = "";
		// String password = "";

		Class.forName("com.mysql.jdbc.Driver");
		// con = DriverManager.getConnection(url, user, password);

		con = getConnection(portNumHme, hmeDBName);
		st = con.createStatement();
//		rs = st.executeQuery("SELECT * FROM healthmessagesexchange2.messages ORDER BY patientId DESC LIMIT 1;");
		rs = st.executeQuery("SELECT * FROM healthmessagesexchange2.messages ORDER BY patientId;");

		parseHmeQuery(rs);

		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// DocumentBuilder db = dbf.newDocumentBuilder();
		// Document dom = db.parse(rs.getString(0));
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

		return conn;
	}

	/**
	 * Run SQL command - executes update, doesn't return resultset
	 */
	public boolean executeUpdate(Connection conn, String command)
			throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it
											// fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) {
				System.out.println("stmt is null. closing1");
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
				System.out.println("stmt is null. closing2");
				// stmt.close();
			}
		}
	}

	/**
	 * Connect to databases and update
	 */
	public void run() {

		/*
		 * Read the data from the healthmessagesexchange table and identify each
		 * field of the message.
		 */

		// Connect to HME database
		Connection connHme = null;
		try {
			connHme = this.getConnection(portNumHme, hmeDBName);
			System.out.println("Connected to HME database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the HME database");
			e.printStackTrace();
			return;
		}

		// read data from HME and identify each field of message
		try {
			String hmeDataQuery = "SELECT * FROM " + hmeTableName + ";";
			ResultSet rs = this.executeQuery(connHme, hmeDataQuery);
			System.out.println("Asked for HME data. ");
			System.out.println("===================================\nHME Data");
			this.parseHmeQuery(rs);

			System.out.println("===================================");
			rs.close();

		} catch (SQLException e) {
			// TODO
		}

		// After identifying and
		// separating you need to store the data into the schema you created.
		// While accessing the HealthMessagesExchange ta- ble’s records, update
		// the last accessed time field of each record with the current system
		// date and time. Simultaneously, the patient table’s
		// xmlCreationDatetime
		// needs to be updated with the current date and time

	}

	public static void parseHmeQuery(ResultSet rs) throws SQLException {
		while (rs.next()) {
			// Retrieve by column name
			// int isbn = rs.getInt("isbn");
			// String title = rs.getString("title");
			// String author = rs.getString("author");
			// int qty_in_stock = rs.getInt("qty_in_stock");
			// double price = rs.getDouble("price");
			// int year_published = rs.getInt("year_published");

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
			String LastName = rs.getString("LastName");
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
			String Id = rs.getString("Id");
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

			System.out.println("MsgId: " + MsgId);
			System.out.println("Last_Accessed: " + Last_Accessed);
			System.out.println("patientId: " + patientId);
			System.out.println("GivenName: " + GivenName);
			System.out.println("FamilyName: " + FamilyName);
			System.out.println("BirthTime: " + BirthTime);
			System.out.println("providerId: " + providerId);
			System.out.println("GuardianNo: " + GuardianNo);
			System.out.println("Relationship: " + Relationship);
			System.out.println("FirstName: " + FirstName);
			System.out.println("LastName: " + LastName);
			System.out.println("phone: " + phone);
			System.out.println("address: " + address);
			System.out.println("city: " + city);
			System.out.println("state: " + state);
			System.out.println("zip: " + zip);
			System.out.println("AuthorId: " + AuthorId);
			System.out.println("AuthorTitle: " + AuthorTitle);
			System.out.println("AuthorFirstName: " + AuthorFirstName);
			System.out.println("AuthorLastName: " + AuthorLastName);
			System.out.println("ParticipatingRole: " + ParticipatingRole);
			System.out.println("PayerId: " + PayerId);
			System.out.println("Name: " + Name);
			System.out.println("PolicyHolder: " + PolicyHolder);
			System.out.println("PolicyType: " + PolicyType);
			System.out.println("Purpose: " + Purpose);
			System.out.println("RelativeId: " + RelativeId);
			System.out.println("Relation: " + Relation);
			System.out.println("age: " + age);
			System.out.println("Diagnosis: " + Diagnosis);
			System.out.println("Id: " + Id);
			System.out.println("Substance: " + Substance);
			System.out.println("Reaction: " + Reaction);
			System.out.println("Status: " + Status);
			System.out.println("LabTestResultId: " + LabTestResultId);
			System.out.println("PatientVisitId: " + PatientVisitId);
			System.out.println("LabTestPerformedDate: " + LabTestPerformedDate);
			System.out.println("LabTestType: " + LabTestType);
			System.out.println("TestResultValue: " + TestResultValue);
			System.out.println("ReferenceRangeHigh: " + ReferenceRangeHigh);
			System.out.println("ReferenceRangeLow: " + ReferenceRangeLow);
			System.out.println("PlanId: " + PlanId);
			System.out.println("Activity: " + Activity);
			System.out.println("ScheduledDate: " + ScheduledDate);


		}
	}

}
