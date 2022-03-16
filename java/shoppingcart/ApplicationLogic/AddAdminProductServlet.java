package shoppingcart.ApplicationLogic;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.DatabaseConnection.AdminProductDao;
import shoppingcart.DatabaseConnection.DatabaseConnection;
import shoppingcart.DatabaseModel.AdminProduct;

/**
 * Servlet implementation class AddAdminProductServlet
 */
@WebServlet("/AddAdminProduct")
public class AddAdminProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productname = request.getParameter("productname");
		String productCategory = request.getParameter("productcategory");
		String productprice = request.getParameter("productprice");
		String productimage = request.getParameter("productimage");
		
		AdminProduct adminProduct = new AdminProduct(productname,productCategory,productprice,productimage);
		try(PrintWriter out=response.getWriter()){
			AdminProductDao adminProductDao = new AdminProductDao(DatabaseConnection.getConnetion());
			if(adminProductDao.addProduct(adminProduct)) {
				response.sendRedirect("admin_product.jsp");
//				request.getRequestDispatcher("/WEB-INF/User_interface/admin_product.jsp").forward(request, response);
			}
			else {
				out.print("worng credential");
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
