package shoppingcart.ApplicationLogic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.DatabaseConnection.*;

import shoppingcart.DatabaseModel.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User userModel = new User(name, email, password);
		try {
			RegisterDatabaseAccess registerDatabaseAccess = new RegisterDatabaseAccess(DatabaseConnection.getConnetion());
			if (registerDatabaseAccess.saveUser(userModel)) {
				
				//request.getRequestDispatcher("/WEB-INF/User_interface/login.jsp").forward(request, response);
			   response.sendRedirect("login.jsp");
			} else {
			    String errorMessage = "User Available";
			    HttpSession regSession = request.getSession();
			    regSession.setAttribute("RegError", errorMessage);
			    
			   // request.getRequestDispatcher("/WEB-INF/User_interface/registration.jsp").forward(request, response);
			    response.sendRedirect("registration.jsp");
			    }
			
		} catch (Exception exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		
	}

}

