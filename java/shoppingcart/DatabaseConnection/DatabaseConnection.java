package shoppingcart.DatabaseConnection;
import java.sql.*;

public class DatabaseConnection {
	private static Connection connection = null;
	
	public static Connection getConnetion() throws ClassNotFoundException, SQLException {
		
		if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jsp_project","root","12345akkanna");
			System.out.print("connected");
		}
		return connection;
			
	}
}
