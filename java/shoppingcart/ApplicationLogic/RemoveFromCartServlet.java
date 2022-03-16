package shoppingcart.ApplicationLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.DatabaseModel.Cart;


@WebServlet("/RemoveFromCart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			String id = request.getParameter("id");
			if(id != null) {
				@SuppressWarnings("unchecked")
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cart_list != null) {
					for(Cart cart:cart_list) {
						if(cart.getId()==Integer.parseInt(id)) {
							cart_list.remove(cart_list.indexOf(cart));
							break;
						}
					}
					
					//request.getRequestDispatcher("/WEB-INF/User_interface/cart.jsp").forward(request, response);
					response.sendRedirect("cart.jsp");
				}
			}else {
				
				//request.getRequestDispatcher("/WEB-INF/User_interface/cart.jsp").forward(request, response);
				response.sendRedirect("cart.jsp");
			}
		}
	}

}
