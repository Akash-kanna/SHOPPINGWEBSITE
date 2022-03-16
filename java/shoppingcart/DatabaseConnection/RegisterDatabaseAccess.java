package shoppingcart.DatabaseConnection;
import java.sql.*;

import shoppingcart.DatabaseModel.*;
public class RegisterDatabaseAccess {
	Connection connection ;

    public RegisterDatabaseAccess(Connection connection) {
        this.connection = connection;
    }
    
    //for register user 
    public boolean saveUser(User user){
        boolean set = false;
        try{
            //Insert register data to database
           String query = "insert into jsp_project.user(name,email,password) values(?,?,?)";
           
           PreparedStatement preparedStatement = this.connection.prepareStatement(query);
           preparedStatement.setString(1, user.getName());
           preparedStatement.setString(2, user.getEmail());
           preparedStatement.setString(3, user.getPassword());
          
           preparedStatement.executeUpdate();
           set = true;
        }catch(Exception exception){
        	exception.printStackTrace();
        }
        return set;
    }

    
    
}
