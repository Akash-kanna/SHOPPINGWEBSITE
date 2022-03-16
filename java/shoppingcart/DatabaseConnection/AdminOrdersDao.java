package shoppingcart.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import shoppingcart.DatabaseModel.Order;

public class AdminOrdersDao {
	Connection connection ;
	
	public AdminOrdersDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Order> getAllOrders(){
		List<Order> orders = new ArrayList<>();
		
		try {
			String query = "select * from jsp_project.orders";
			PreparedStatement prepareStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("o_id");
				int userID = resultSet.getInt("p_id");
				int orderQuantity = resultSet.getInt("o_quantity");
				String orderedDate = resultSet.getString("o_date");
				
				Order orderRow = new Order(id,userID,orderQuantity,orderedDate);
				orders.add(orderRow);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return orders;
		
	}
}
