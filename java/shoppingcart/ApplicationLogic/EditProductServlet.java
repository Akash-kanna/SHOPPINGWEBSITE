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
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/EditProduct")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pname = request.getParameter("name");
		String pcategory = request.getParameter("category");
		String pprice =request.getParameter("price");
		String pimage=request.getParameter("image");
		AdminProduct adminProduct = new AdminProduct(pname,pcategory,pprice,pimage);
		try(PrintWriter out = response.getWriter()){
			AdminProductDao apdao = new AdminProductDao(DatabaseConnection.getConnetion());
			if(apdao.addProduct(adminProduct)) {
				response.sendRedirect("admin_product.jsp");
			}else {
				out.print("failes");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
