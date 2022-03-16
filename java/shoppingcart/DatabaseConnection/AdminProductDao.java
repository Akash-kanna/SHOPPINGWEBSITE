package shoppingcart.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.*;

import shoppingcart.DatabaseModel.AdminProduct;


public class AdminProductDao {
	Connection connection ;

	public AdminProductDao(Connection connection) {
		this.connection = connection;
	}
	//INSERT
	public boolean addProduct(AdminProduct adminProduct) {
		boolean test = false;
		
		try {
			String query = "insert into jsp_project.products(name,category,price,image) values(?,?,?,?)";
			PreparedStatement prepareStatement = this.connection.prepareStatement(query);
			prepareStatement.setString(1, adminProduct.getName());
			prepareStatement.setString(2, adminProduct.getCategory());
			prepareStatement.setString(3, adminProduct.getPrice());
			prepareStatement.setString(4, adminProduct.getImage());
			prepareStatement.executeUpdate();
			test = true;
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return test;
	}
	
	//RETRIVE
	public List<AdminProduct> getAllProducts(){
		List<AdminProduct> product = new ArrayList<>();
		
		try {
			String query = "Select * from jsp_project.products";
			PreparedStatement prepareStatement = this.connection.prepareStatement(query);
			ResultSet resultset = prepareStatement.executeQuery();
			
			while(resultset.next()) {
				int id = resultset.getInt("id");
				String proname = resultset.getString("name");
				String procat = resultset.getString("category");
				String proprice = resultset.getString("price");
				String proimage = resultset.getString("image");
				
				AdminProduct adminProduct = new AdminProduct(id,proname,procat,proprice,proimage);
				product.add(adminProduct);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return product;
	}
	
	//DELETE
	public void deleteProduct(int id) {
		try {
			String query = "delete from jsp_project.products where id=?";
			PreparedStatement preparestatement = this.connection.prepareStatement(query);
			preparestatement.setInt(1, id);
			preparestatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//select Single product
	public AdminProduct getSingleProduct(int id) {
		AdminProduct adminProduct = null;
		
		try {
			String query = "select * from jsp_project.products where id=?";
			PreparedStatement preparestatement = this.connection.prepareStatement(query);
			preparestatement.setInt(1, id);
			ResultSet resultset = preparestatement.executeQuery();
			
			while(resultset.next()) {
				
				int pid = resultset.getInt("id");
				String pname = resultset.getString("name");
				String pcategory = resultset.getString("category");
				String pprice = resultset.getString("price");
				String pimage = resultset.getString("image");
				
				adminProduct = new AdminProduct(pid,pname,pcategory,pprice,pimage);
				
				
			}
			
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return adminProduct;		
		
		
	}
	//update
	public boolean updateProduct(AdminProduct adminProduct) {
		boolean set = false;
		
		try {
			String query = "update jsp_project.products set name=?, category=?, Price=?, image=? where (id=?)";
			PreparedStatement preparestatement = this.connection.prepareStatement(query);
			preparestatement.setString(1, adminProduct.getName());
			preparestatement.setString(2, adminProduct.getCategory());
			preparestatement.setString(3, adminProduct.getPrice());
			preparestatement.setString(4, adminProduct.getImage());
			preparestatement.setInt(5, adminProduct.getId());
			preparestatement.executeUpdate();
			set= true;
			
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return set;
		
		
	}
	
	
	
}


















