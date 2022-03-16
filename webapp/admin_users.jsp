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

<%
AdminUserDao userData = new AdminUserDao(DatabaseConnection.getConnetion());
List<User> user = userData.getAllUser();
request.setAttribute("User_List",user);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="Styles.css">
<link rel="stylesheet" href="index.css">

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


<div class="container">
		<div class="inner">
			<div class="row">
				
				<div class="col-md-9">
					<h3>User Information From Database</h3>
					<table class="table">
						<thead class="bg-light">
							<tr>
								<th scope="col">User Name</th>
								<th scope="col">Email</th>
								<th scope="col">Password</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach var="tempUsers" items="${User_List }">
								<tr>
									<td>${tempUsers.name }</td>
									<td>${tempUsers.email }</td>
									<td>${tempUsers.password }</td>
									<td><a href="DeleteUser?id=${tempUsers.id }">Remove</a></td>
								</tr>
								
								</c:forEach>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>






<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>