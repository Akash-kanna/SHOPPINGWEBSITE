package shoppingcart.ApplicationLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.DatabaseModel.Cart;

/**
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet("/Quantity-Inc-Dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter();){
			String action = request.getParameter("action");
			int id=Integer.parseInt(request.getParameter("id"));
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			
			if(action != null && id>=1) {
				if(action.equals("inc")) {
					for(Cart cart:cart_list) {
						if(cart.getId() == id) {
							int quantity = cart.getQuantity();
							quantity++;
							cart.setQuantity(quantity);
							
							//request.getRequestDispatcher("/WEB-INF/User_interface/cart.jsp").forward(request, response);
							response.sendRedirect("cart.jsp");
						}
					}
				}

					if(action.equals("dec")) {
						for(Cart cart:cart_list) {
							if(cart.getId() == id && cart.getQuantity()>1) {
								int quantity = cart.getQuantity();
								quantity--;
								cart.setQuantity(quantity);
								break;
							}
						}
						
						//request.getRequestDispatcher("/WEB-INF/User_interface/cart.jsp").forward(request, response);
						response.sendRedirect("cart.jsp");
					}
			}else {
				
			//	request.getRequestDispatcher("/WEB-INF/User_interface/cart.jsp").forward(request, response);
				response.sendRedirect("cart.jsp");
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
