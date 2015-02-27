import java.io.IOException;

import javax.xml.parsers.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class xmlparse {
	
	private final static String userName = "root";
	private final static String password = "";
	private final static String serverName = "localhost";
	private final static int portNumHme = 3306;
	private final static int portNumHospital = 3307;
	private final static String hmeDBName = "healthmessagesexchange2";
	private final static String hospitalDBName = "hospitalrecords";
	private final static String hmeTableName = "messages";
	
	
	public static void main() throws ClassNotFoundException, SQLException {
		try {

			Connection con = null;
			Statement st = null;
			ResultSet rs = null;

//			String url = "jdbc:mysql://localhost:3306/dbname";
//			String user = "";
//			String password = "";

			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection(url, user, password);
			
			con = getConnection(portNumHme, hmeDBName);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM healthmessagesexchange.messagequeue ORDER BY control_id DESC LIMIT 1;");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document dom = db.parse(rs.getString(0));

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
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

		conn = DriverManager.getConnection("jdbc:mysql://" + serverName
				+ ":" + portNum + "/" + dbName, connectionProps);

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
	
	public void parseHmeQuery(ResultSet rs) throws SQLException {
		while (rs.next()) {
			// Retrieve by column name
			int isbn = rs.getInt("isbn");
			String title = rs.getString("title");
			String author = rs.getString("author");
			int qty_in_stock = rs.getInt("qty_in_stock");
			double price = rs.getDouble("price");
			int year_published = rs.getInt("year_published");

//			MsgId` varchar(100) DEFAULT NULL,
//			Last_Accessed` varchar(100) DEFAULT NULL,
//			patientId` varchar(100) DEFAULT NULL,
//			GivenName` varchar(100) DEFAULT NULL,
//			FamilyName` varchar(100) DEFAULT NULL,
//			BirthTime` varchar(100) DEFAULT NULL,
//			providerId` varchar(100) DEFAULT NULL,
//			GuardianNo` varchar(100) DEFAULT NULL,
//			Relationship` varchar(100) DEFAULT NULL,
//			FirstName` varchar(100) DEFAULT NULL,
//			LastName` varchar(100) DEFAULT NULL,
//			phone` varchar(100) DEFAULT NULL,
//			address` varchar(100) DEFAULT NULL,
//			  `city` varchar(100) DEFAULT NULL,
//			  `state` varchar(100) DEFAULT NULL,
//			  `zip` varchar(100) DEFAULT NULL,
//			  `AuthorId` varchar(100) DEFAULT NULL,
//			  `AuthorTitle` varchar(100) DEFAULT NULL,
//			  `AuthorFirstName` varchar(100) DEFAULT NULL,
//			  `AuthorLastName` varchar(100) DEFAULT NULL,
//			  `ParticipatingRole` varchar(100) DEFAULT NULL,
//			  `PayerId` varchar(100) DEFAULT NULL,
//			  `Name` varchar(100) DEFAULT NULL,
//			  `PolicyHolder` varchar(100) DEFAULT NULL,
//			  `PolicyType` varchar(100) DEFAULT NULL,
//			  `Purpose` varchar(100) DEFAULT NULL,
//			  `RelativeId` varchar(100) DEFAULT NULL,
//			  `Relation` varchar(100) DEFAULT NULL,
//			  `age` varchar(100) DEFAULT NULL,
//			  `Diagnosis` varchar(100) DEFAULT NULL,
//			  `Id` varchar(100) DEFAULT NULL,
//			  `Substance` varchar(100) DEFAULT NULL,
//			  `Reaction` varchar(100) DEFAULT NULL,
//			  `Status` varchar(100) DEFAULT NULL,
//			  `LabTestResultId` varchar(100) DEFAULT NULL,
//			  `PatientVisitId` varchar(100) DEFAULT NULL,
//			  `LabTestPerformedDate` varchar(100) DEFAULT NULL,
//			  `LabTestType` varchar(100) DEFAULT NULL,
//			  `TestResultValue` varchar(100) DEFAULT NULL,
//			  `ReferenceRangeHigh` varchar(100) DEFAULT NULL,
//			  `ReferenceRangeLow` varchar(100) DEFAULT NULL,
//			  `PlanId` varchar(100) DEFAULT NULL,
//			  `Activity` varchar(100) DEFAULT NULL,
//			  `ScheduledDate` varchar(100) DEFAULT NULL
			
		}
	}
	
	
	
}













