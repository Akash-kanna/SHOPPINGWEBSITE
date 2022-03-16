package shoppingcart.ApplicationLogic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cardValidation
 */
@WebServlet("/cardValidation")
public class cardValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
PrintWriter out = response.getWriter();
		
		
		String cardNumber = request.getParameter("cardnumber");
		
		
		if(cardNumber != null) {
			int[] creditCardInt = new int[cardNumber.length()];
			for(int i=0;i<cardNumber.length();i++) {
				creditCardInt[i] = Integer.parseInt(cardNumber.substring(i,i+1));
			}
			
			for(int i=creditCardInt.length-2;i>=0;i=i-2) {
				int tempValue = creditCardInt[i];
				tempValue = tempValue * 2;
				if(tempValue > 9) {
					tempValue = tempValue %10+1;
				}
				creditCardInt[i] = tempValue;
			}
			
			int total=0;
			for(int i=0;i<creditCardInt.length;i++) {
				total +=creditCardInt[i];
			}
			if(total%10==0) {
				response.sendRedirect("orders.jsp");
			}
			else {
				out.println("your Credit card number is not valid please check it and make the payment...");
			}
		
		
		
	}
	}
}
