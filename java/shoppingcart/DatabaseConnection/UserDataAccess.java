package shoppingcart.DatabaseConnection;
import java.sql.*;

import shoppingcart.DatabaseModel.User;
public class UserDataAccess {
	private Connection connection;
//	private String query;
//	private PreparedStatement preparestatement;
//	private ResultSet resultset;
	
	
	public UserDataAccess(Connection connection) {
		this.connection=connection;
	}
	
	//login
	public User userLogin(String email,String password) {
		User user = null;
		try {
			String query = "select * from jsp_project.user where email=? and password=?";
			PreparedStatement preparestatement = this.connection.prepareStatement(query);
			preparestatement.setString(1, email);
			preparestatement.setString(2, password);
			ResultSet resultset = preparestatement.executeQuery();
			if(resultset.next()) {
				user = new User();
				user.setId(resultset.getInt("id"));
				user.setName(resultset.getString("name"));
				user.setEmail(resultset.getString("email"));
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println(exception.getMessage());
		}
		return user;
	}	

}






















