package shoppingcart.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import shoppingcart.DatabaseModel.User;

public class AdminUserDao {
	Connection connection ;

	public AdminUserDao(Connection connection) {
		this.connection = connection;
	}
	
	//display
	public List<User> getAllUser(){
		List<User> user = new ArrayList<>();
		
		try {
			String query = "select * from jsp_project.user";
			PreparedStatement prepareStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String uname = resultSet.getString("name");
				String uemail = resultSet.getString("email");
				String upassword = resultSet.getString("password");
				
				User userrow = new User(id,uname,uemail,upassword);
				user.add(userrow);
			}
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return user;
		
	}
	
	//delete
	
	public void deleteUser(int id) {
		try {
			String query = "delete from jsp_project.user where id=?";
			PreparedStatement prepareStatement = this.connection.prepareStatement(query);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
