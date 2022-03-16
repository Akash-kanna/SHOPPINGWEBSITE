package shoppingcart.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import shoppingcart.DatabaseModel.*;

public class OrderDataAccess {
	private Connection connection;
	private String query;
	private PreparedStatement preparestatement;
	private ResultSet resultset;
	
	public OrderDataAccess(Connection connection) {
		this.connection = connection;	
	}
	
	public boolean insertOrder(Order model) {
		
		boolean result = false;
		
		try {
			query= "INSERT INTO jsp_project.orders (p_id, u_id, o_quantity, o_date) VALUES (?,?,?,?)";
			
			preparestatement = this.connection.prepareStatement(query);
			preparestatement.setInt(1, model.getId());
			preparestatement.setInt(2, model.getUid());
			preparestatement.setInt(3, model.getQuantity());
			preparestatement.setString(4, model.getDate());
			preparestatement.executeUpdate();
			result = true;
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return result;
	}
	
	public List<Order> userOrders(int id){
		List<Order> list = new ArrayList<>();
		
		try {
			query = "select * from jsp_project.orders where u_id=? order by orders.o_id desc";
			preparestatement = this.connection.prepareStatement(query);
			preparestatement.setInt(1, id);
			resultset= preparestatement.executeQuery();
			
			while(resultset.next()) {
				Order order = new Order();
				ProductDataAccess productDao = new ProductDataAccess(this.connection);
				int productId=resultset.getInt("p_id");
				
				Product product = productDao.getSingleProduct(productId);
				order.setOrderId(resultset.getInt("o_id"));
				order.setId(productId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice()*resultset.getInt("o_quantity"));
				order.setQuantity(resultset.getInt("o_quantity"));
				order.setDate(resultset.getString("o_date"));
				list.add(order);
				
			}
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return list;
	}
	
	
	public void cancelOrder(int id) {
		try {
			query = "delete from jsp_project.orders where o_id=?";
			preparestatement = this.connection.prepareStatement(query);
			preparestatement.setInt(1, id);
			preparestatement.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	
}










