package shoppingcart.ApplicationLogic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			if(request.getSession().getAttribute("authentication")!=null) {
				request.getSession().removeAttribute("authentication");
				
				//request.getRequestDispatcher("/WEB-INF/User_interface/login.jsp").forward(request, response);
				response.sendRedirect("login.jsp");
			}else {
				
				//request.getRequestDispatcher("/WEB-INF/User_interface/index.jsp").forward(request, response);
				response.sendRedirect("index.jsp");
			}
		}
	}

}
