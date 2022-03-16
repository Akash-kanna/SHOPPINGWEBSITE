package shoppingcart.ApplicationLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.DatabaseConnection.DatabaseConnection;
import shoppingcart.DatabaseConnection.OrderDataAccess;
import shoppingcart.DatabaseModel.*;


@WebServlet("/Cart-Check-Out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			//retrive cart products
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			//user authentication
			User authentication = (User)request.getSession().getAttribute("authentication");
			if(cart_list == null) {
				response.sendRedirect("cart.jsp");
			}
			if(cart_list != null && authentication != null) {
				
				for(Cart cart:cart_list) {
					Order order = new Order();
					order.setId(cart.getId());
					order.setUid(authentication.getId());
					order.setQuantity(cart.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDataAccess  orderDataaccess = new OrderDataAccess(DatabaseConnection.getConnetion());
					boolean result = orderDataaccess.insertOrder(order);
					
					if(!result) break;
				}
				cart_list.clear();
				if(authentication == null) {
					
					//request.getRequestDispatcher("/WEB-INF/User_interface/login.jsp").forward(request, response);
					
					response.sendRedirect("login.jsp");
				}else {
					response.sendRedirect("payment.jsp");
				}
				
				
			}else {                       //request.getRequestDispatcher("/WEB-INF/User_interface/login.jsp").forward(request, response);
				if(authentication == null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
				
				//request.getRequestDispatcher("/WEB-INF/User_interface/cart.jsp").forward(request, response);
			}
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
