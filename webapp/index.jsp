<%@page import="java.util.*"%>
<%@page import="shoppingcart.ApplicationLogic.*"%>
<%@page import="shoppingcart.DatabaseModel.*"%>
<%@page import="shoppingcart.DatabaseConnection.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User authentication = (User) request.getSession().getAttribute("authentication");
if (authentication != null) {
	request.setAttribute("authentication", authentication);
}

ProductDataAccess productDataaccess = new ProductDataAccess(DatabaseConnection.getConnetion());
List<Product> products = productDataaccess.getAllProducts();
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
<title>welcome</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="Styles.css">
<link rel="stylesheet" href="index.css">
<style type="text/css">
.card{
height:23rem
}
</style>
</head>


<body>

	<!-- Header-nav bar -->
	<div class="navigationbar">
	<nav class="navbar navbar-expand-lg navbar-light bg-light ">
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
</div>
	<!-- Header-nav bar ends -->



<section class="home" id="home">
    <div class="content">
        <h5>we care for your healthy living</h5>
        <p>The art of medicine consists in amusing the patient while nature cures the diseases.</p>
        <button class="viewprod" style="background-color: #d3ad7f;border:none;" ><a href="#category" style="text-decoration: none;color:white">view product</a></button>
        
    </div>
</section>

	<div class="container">
		<div class="card-header my-3" ><h3 id="category">Categories</h3></div>
		<div class="row">
			<div class="col-md-3 my-3">
				<div class="card">
					<img src="Categoryimg/covid-essentials.jpg" class="card-img-top" alt="Covid-essentials">
					<div class="card-body" >
						<h5 class="card-title">Covid Essentials</h5>
						<div class="mt-3 d-fle justify-content-between">
							<a href="products.jsp" class="btn btn-primary" style="margin-left: 2.5rem">View products</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 my-3">
				<div class="card">
					<img src="Categoryimg/Body_Care.jpg" class="card-img-top" alt="Covid-essentials">
					<div class="card-body">
						<h5 class="card-title">Body Care</h5>
						<div class="mt-3 d-fle justify-content-between">
							<a href="products.jsp" class="btn btn-primary" style="margin-left: 2.5rem">View products</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 my-3">
				<div class="card">
					<img src="Categoryimg/face_care.jpg" class="card-img-top" alt="Covid-essentials">
					<div class="card-body">
						<h5 class="card-title">Face Care</h5>
						<div class="mt-3 d-fle justify-content-between">
							<a href="products.jsp" class="btn btn-primary" style="margin-left: 2.5rem">View products</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 my-3">
				<div class="card">
					<img src="Categoryimg/skin-care.jpg" class="card-img-top" alt="Covid-essentials">
					<div class="card-body">
						<h5 class="card-title">Skin Care</h5>
						<div class="mt-3 d-fle justify-content-between">
							<a href="products.jsp" class="btn btn-primary" style="margin-left: 2.5rem">View products</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 my-3">
				<div class="card">
					<img src="Categoryimg/Hair_care.jpg" class="card-img-top" alt="Covid-essentials">
					<div class="card-body">
						<h5 class="card-title">Hair Care</h5>
						<div class="mt-3 d-fle justify-content-between">
							<a href="products.jsp" class="btn btn-primary" style="margin-left: 2.5rem">View products</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 my-3">
				<div class="card">
					<img src="Categoryimg/shampoos-and-conditioners.jpg" class="card-img-top" alt="Covid-essentials">
					<div class="card-body">
						<h5 class="card-title">Shampoos-Conditioners</h5>
						<div class="mt-3 d-fle justify-content-between">
							<a href="products.jsp" class="btn btn-primary" style="margin-left: 2.5rem">View products</a>
						</div>
					</div>
				</div>
			</div>



		</div>
	</div>

	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>