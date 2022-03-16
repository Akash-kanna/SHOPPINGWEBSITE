<%@page import="java.util.*"%>

<%@page import="shoppingcart.DatabaseModel.*"%>
<%@page import="shoppingcart.DatabaseConnection.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="shoppingcart.DatabaseModel.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
	User authentication = (User) request.getSession().getAttribute("authentication");
%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 

<%

int id=Integer.parseInt(request.getParameter("id"));
	AdminProductDao adminProductDao = new AdminProductDao(DatabaseConnection.getConnetion());
	AdminProduct adminProduct = adminProductDao.getSingleProduct(id);
	request.setAttribute("editproduct", adminProduct);

%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="Styles.css">
<link rel="stylesheet" href="index.css">



<style>
.inner {
	position: relative;
	margin: 0 auto;
	width: 650px;
	display: block;
	padding: 50px 0;
}

h3 {
	text-align: center;
	border-bottom: 2px solid midnightblue;
	margin-bottom: 20px;
}

a {
	color: #fff;
	text-decoration: none;
}

</style>
</head>
 

<body>

	<!-- Header-nav bar -->

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"><i
				class="fas fa-hand-holding-medical"></i>Medikart</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse " id="navbarSupportedContent">
			<ul class="navbar-nav me-right mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="admin.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="admin_product.jsp">Products</a></li>
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="admin_users.jsp">Users</a></li>
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="admin_orders.jsp">Orders</a></li>

				<%
        if(authentication != null){ %>
				<li class="nav-item"><a class="nav-link" href="Logout"><i class="fas fa-sign-out-alt"></i></a></li>
				<%}else{ %>
				<li class="nav-item"><a class="nav-link" href="login.jsp"><i class="fas fa-sign-in-alt"></i></a></li>
				<%} %>

			</ul>
		</div>
	</nav>
	<!-- Header-nav bar ends -->

	<div class="inner">
		<div class=" container" style="align-content: center;">
			<div class="row">
				<div class="col-12">
					<h3>Edit Product Details</h3>
					<form action="EditProduct" method="post">
						<div class="form-group">
							<label>Product ID</label> 
							<input class="form-control" name="id"value="${editproduct.id }" required>
						</div>
						
						<div class="form-group">
							<label>Category</label>
							 <select id="inputState"class="form-control" name="category" style="width:40%" required>
								<option selected disabled>Choose Category</option>
								<option value="Covid-Essentials">Covid-Essentials</option>
								<option value="Body Care">Body Care</option>
								<option value="Face Care">Face Care</option>
								<option value="Hair Care">Hair Care</option>
								<option value="Skin Care">Skin Care</option>
								<option value="Mens Care">Mens Care</option>
								<option value="Shampoos & Conditioners">Shampoos-Conditioners</option>
							</select>
						</div>
						
						<div class="form-group">
							<label>Product Name</label> 
							<input class="form-control"name="name" value="${editproduct.name }" required>
						</div>
						
						<div class="form-group">
							<label>Product Price</label>
							<input class="form-control"name="price" value="${editproduct.price}" required>
						</div>
						
						
						<div class="form-group">
							<label>Product Image</label>
							<input class="form-control"name="image" value="${editproduct.image}" required>
						</div>
						
						<button type="submit" class="btn btn-primary">Submit</button>
						<button class="btn btn-primary">
							<a href="admin_product.jsp">Cancel</a>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>






	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>