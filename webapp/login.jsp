<%@page import="java.util.*"%>
<%@page import="shoppingcart.ApplicationLogic.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="shoppingcart.DatabaseModel.*"%>    
    <%
	User authentication = (User) request.getSession().getAttribute("authentication");
	if(authentication!=null){
		response.sendRedirect("index.jsp");
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
<title>login page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="Styles.css">
	<style>
        .card{
	width:20rem;
	height:25rem;
	border-radius: 2rem;
}
    </style>
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
    <div class="card  mx-auto my-5" style="border-radius: .5rem;">
        <div class="card-header text-center">User Login</div>
        <div class="card-body">
            <form action="user-LoginServlet" method="post" class="form">

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" name="login-email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="login-password" placeholder="Password" required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn" style="background-color: #d3ad7f;color:white">login</button>
                </div>
            </form>
            <p style="text-align:center">Dont have an account?<a style="text-decoration: none;" href="registration.jsp">Register</a></p>
        </div>
    </div>
</div>





<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>