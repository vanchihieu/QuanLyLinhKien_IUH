package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection openConnection() throws ClassNotFoundException, SQLException  {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost:1433;databaseName=DemoDB";
		String username = "sa";
		String password = "sapassword";
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
}
