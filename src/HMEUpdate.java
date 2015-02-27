import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class HMEUpdate {

	private final String userName = "root";
	private final String password = "";
	private final String serverName = "localhost";
	private final int portNumHme = 3306;
	private final int portNumHospital = 3306;
	private final String hmeDBName = "healthmessagesexchange2";
	private final String hospitalDBName = "hospitalrecords";
	
	
	/*
	 * Get database connection
	 * 
	 * @return
	 * @throws SQL Exceptoin
	 */
	public Connection getConnection(int portNum, String dbName) throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName
				+ ":" + portNum + "/" + dbName, connectionProps);

		return conn;
	}
	
}
