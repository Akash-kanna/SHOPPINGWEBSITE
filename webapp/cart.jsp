<%@page import="java.util.*"%>
<%@page import="shoppingcart.DatabaseConnection.ProductDataAccess"%>
<%@page import="shoppingcart.DatabaseConnection.DatabaseConnection"%>
<%@page import="shoppingcart.ApplicationLogic.*"%>
<%@page import="shoppingcart.DatabaseModel.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User authentication = (User) request.getSession().getAttribute("authentication");
if (authentication != null) {
	request.setAttribute("authentication", authentication);
}
@SuppressWarnings("unchecked")
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if(cart_list != null){
	ProductDataAccess productDataaccess = new ProductDataAccess(DatabaseConnection.getConnetion());
	cartProduct = productDataaccess.getCartProducts(cart_list);
	double total = productDataaccess.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cart page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="Styles.css">

</head>
<body>
	<!-- Header-nav bar -->
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><i class="fas fa-hand-holding-medical"></i>Medikart</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </div>
  <div class="collapse navbar-collapse " id="navbarSupportedContent">
      <ul class="navbar-nav me-right mb-2 mb-lg-0" >
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="index.jsp">Home</a></li>
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="products.jsp">Products</a></li>
        <li class="nav-item"> <a class="nav-link" href="cart.jsp">Cart<span style="color:red;padding:.5rem">${ cart_list.size() }</span></a></li>
        
        <%
        if(authentication != null){ %>
        <li class="nav-item"><a class="nav-link" href="orders.jsp">orders</a></li>
        <li class="nav-item"><a class="nav-link" href="Logout"><i class="fas fa-sign-out-alt"></i></a></li>
        <%}else{ %>
        <li class="nav-item"><a class="nav-link" href="login.jsp"><i class="fas fa-sign-in-alt"></i></a></li>
        <%} %>
      </ul>
     
    </div>
</nav>	
	<!-- Header-nav bar ends -->
	

	<div class="container">
		<table class="table table-loght">
			<tr></tr>
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">cancel</th>
				</tr>
			</thead>

			<tbody>
			<%if(cart_list != null){
				for(Cart cartitem:cartProduct){ %>
					<tr>
					<td><%= cartitem.getName() %></td>
					<td><%= cartitem.getCategory() %></td>
					<td><%= cartitem.getPrice() %></td>
					<td>
						<form action="Order-Now" method="post" class="form-inline"
							style="width: 11rem">
							<input type="hidden" name="id" value="<%= cartitem.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">

								<a class="btn btn-sm btn-decre" href="Quantity-Inc-Dec?action=dec&id=<%=cartitem.getId()%>"><i class="fas fa-minus-square" style="font-size:25px"></i></a>
									 <input type="text"name="quantity" class="form-control" value="<%=cartitem.getQuantity() %>" readonly>
								<a class="btn btn-sm btn-incre" href="Quantity-Inc-Dec?action=inc&id=<%=cartitem.getId()%>"><i class="fas fa-plus-square" style="font-size:25px"></i></a>
								
							</div>
							
						</form>
					</td>
					
					<td><a class="btn btn-sm btn-danger" href="RemoveFromCart?id=<%= cartitem.getId() %>">Remove</a></td>
				</tr>
			<%	}
			}
			
			
			%>
			

			</tbody>
		</table>
		<div class="d-flex py-3">
			<h3>Total price:INR ${ (total)>0?total:0 }</h3>
			<a class="mx-3 btn btn-primary" href="Cart-Check-Out">check out</a>
			<!--  <a class="mx-3 btn btn-primary" href="payment.jsp">check out</a>-->
		</div>

	</div>


	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>


















