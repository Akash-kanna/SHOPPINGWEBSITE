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
import shoppingcart.DatabaseModel.Cart;
import shoppingcart.DatabaseModel.Order;
import shoppingcart.DatabaseModel.User;
/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/Order-Now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			
			User authentication = (User)request.getSession().getAttribute("authentication");
			if(authentication != null) {
				
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity <=0) {
					productQuantity = 1;
				}
				
				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(authentication.getId());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDataAccess orderDataAccess = new OrderDataAccess(DatabaseConnection.getConnetion());
				boolean result = orderDataAccess.insertOrder(orderModel);
				
				if(result) {
					@SuppressWarnings("unchecked")
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if(cart_list != null) {
						for(Cart cart:cart_list) {
							if(cart.getId()==Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(cart));
								break;
							}
						}
					}
					
					//request.getRequestDispatcher("/WEB-INF/User_interface/order.jsp").forward(request, response);
					response.sendRedirect("orders.jsp");
				}else {
					out.print("order failed");
				}
				
			}else {
				
				//request.getRequestDispatcher("/WEB-INF/User_interface/login.jsp").forward(request, response);
				response.sendRedirect("login.jsp");
			}
		} catch (Exception exception) {
			// TODO Auto-generated catch block
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
