package shoppingcart.ApplicationLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.DatabaseModel.Cart;

@WebServlet("/AddToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cart = new Cart();
			cart.setId(id);
			cart.setQuantity(1);
			
			HttpSession session = request.getSession();
			
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart-list");
			
			if(cart_list == null) {
				cartList.add(cart); 
				session.setAttribute("cart-list", cartList);
				//response.sendRedirect("index.jsp");
				//request.getRequestDispatcher("/WEB-INF/User_interface/products.jsp").forward(request, response);
				response.sendRedirect("products.jsp");
			}else {
				cartList = cart_list;
				boolean exist = false;
				
				for(Cart cartitem:cartList) {
					if(cartitem.getId() == id) {
						exist = true;
						
						//request.getRequestDispatcher("/WEB-INF/User_interface/cart.jsp").forward(request, response);
						response.sendRedirect("cart.jsp");
					}
				}
				if(!exist) {
					cartList.add(cart);
					
				//request.getRequestDispatcher("/WEB-INF/User_interface/products.jsp").forward(request, response);
					
					response.sendRedirect("products.jsp");
				}
			}
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}


}










