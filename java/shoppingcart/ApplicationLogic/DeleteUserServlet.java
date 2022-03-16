package shoppingcart.ApplicationLogic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.DatabaseConnection.AdminUserDao;
import shoppingcart.DatabaseConnection.DatabaseConnection;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int uid = Integer.parseInt(request.getParameter("id"));
			AdminUserDao adminUserDao = new AdminUserDao(DatabaseConnection.getConnetion());
			adminUserDao.deleteUser(uid);
			
			//request.getRequestDispatcher("/WEB-INF/User_interface/admin_user.jsp").forward(request, response);
			
			response.sendRedirect("admin_users.jsp");
		}catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
