<%@page import="java.util.*"%>
<%@page import="shoppingcart.DatabaseConnection.*"%>
<%@page import="shoppingcart.ApplicationLogic.*"%>
<%@page import="shoppingcart.DatabaseModel.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	User authentication = (User) request.getSession().getAttribute("authentication");
	List<Order> orders = null;
	if(authentication!=null){
		request.setAttribute("authentication",authentication);
		orders = new OrderDataAccess(DatabaseConnection.getConnetion()).userOrders(authentication.getId());
	}else{
		response.sendRedirect("login.jsp");
	}
	@SuppressWarnings("unchecked")
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>orders</title>
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
    <div class="card-header my-3"> All Orders</div>
    <table class="table table-light">
        <thead>
            <tr>
           		<th scope="col">Order_ID</th>
                <th scope="col">Date</th>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
                <th scope="col">Cancel</th>
            </tr>
        </thead>

        <tbody>
            
            <%
            	if(orders !=null){
            		for(Order order:orders){ %>
            			<tr>
            			<td><%= order.getOrderId() %> </td>
            			<td><%= order.getDate() %> </td>
            			<td><%= order.getName() %> </td>
            			<td><%= order.getCategory() %> </td>
            			<td><%= order.getQuantity() %> </td>
            			<td><%= order.getPrice() %> </td>
            			<td><a class="btn btn-sm btn-danger" href="Cancel-Order?id=<%= order.getOrderId()%>">Cancel</a></td>
            		</tr>
            	<%}
            	}
            %>
        </tbody>

    </table>
</div>

<div class="container ce-3">
<h5>Your order is placed sucessfully! and it will delivered within 7 working days....</h5>
</div>


<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>