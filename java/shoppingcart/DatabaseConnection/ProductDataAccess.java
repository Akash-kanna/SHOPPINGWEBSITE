package shoppingcart.DatabaseConnection;

import java.sql.*;
import java.util.*;

import shoppingcart.DatabaseModel.*;

public class ProductDataAccess {
	private Connection connection;
	private String query;
	private PreparedStatement preparestatement;
	private ResultSet resultset;
	
	public ProductDataAccess(Connection connection) {
		this.connection = connection;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products  = new ArrayList<Product>();
		try {
			query = "select * from jsp_project.products";
			preparestatement = this.connection.prepareStatement(query);
			resultset = preparestatement.executeQuery();
			while(resultset.next()) {
				Product product = new Product();
				product.setId(resultset.getInt("id"));
				product.setName(resultset.getString("name"));
				product.setCategory(resultset.getString("category"));
				product.setPrice(resultset.getDouble("price"));
				product.setImage(resultset.getString("image"));
				products.add(product);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return products;
	}
	
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query = "select * from jsp_project.products where id=?";
					preparestatement = this.connection.prepareStatement(query);
					preparestatement.setInt(1, item.getId());
					resultset = preparestatement.executeQuery();
					while(resultset.next()) {
						Cart cart = new Cart();
						cart.setId(resultset.getInt("id"));
						cart.setName(resultset.getString("name"));
						cart.setCategory(resultset.getString("category"));
						cart.setPrice(resultset.getDouble("price")*item.getQuantity());
						cart.setQuantity(item.getQuantity());
						products.add(cart);
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}		
		return products;
	}
	
	public Product getSingleProduct(int id) {
		Product product = null;
		
		try {
			query = "select * from jsp_project.products where id=?";
			preparestatement = this.connection.prepareStatement(query);
			preparestatement.setInt(1, id);
			resultset = preparestatement.executeQuery();
			
			while(resultset.next()) {
				product=new Product();
				product.setId(resultset.getInt("id"));
				product.setName(resultset.getString("name"));
				product.setCategory(resultset.getString("category"));
				product.setPrice(resultset.getDouble("price"));
				
			}
			
		}catch (Exception exception) {
			exception.printStackTrace();
		}		
		
		return product;
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum=0;
		
		try {
			if(cartList.size()>0) {
				for(Cart cartItem:cartList) {
					query = "select price from jsp_project.products where id=?";
					preparestatement = this.connection.prepareStatement(query);
					preparestatement.setInt(1, cartItem.getId());
					resultset = preparestatement.executeQuery();
					
					while(resultset.next()) {
						sum+=resultset.getDouble("price")*cartItem.getQuantity();
					}
				}
			}
			
		} catch (Exception exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		
		return sum;
	}
	
	public boolean saveUsers(Product product) {
		boolean set = false;
		
		try {
			query = "insert into jsp_project.products(name, category, price,image) values(?,?,?,?)";
			preparestatement = this.connection.prepareStatement(query);
			preparestatement.setString(1, product.getName());
			preparestatement.setString(2, product.getCategory());
			preparestatement.setDouble(3, product.getPrice());
			preparestatement.setString(4, product.getImage());
			
			preparestatement.executeUpdate();
			set = true;
			
			
			
			
		}catch (Exception exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		return set;
	}
	
	
}
