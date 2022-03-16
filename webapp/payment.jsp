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

<style>
    *{
        margin: 0;padding: 0;
        box-sizing: border-box;
        outline: none;border: none;
        text-decoration: none;
        
    }

    .container{
        min-height: 100vh;
        
        display: flex;
        align-items: center;
        justify-content: center;
        flex-flow: column;
        padding-bottom: 60px;
    }

    .container form{
        background: #fff;
        border-radius: 5px;
        box-shadow: 0 10px 15px rgba(0,0,0,.1);
        padding: 20px;
        width: 600px;

    }

    .container form .inputBox{
        margin-top: 20px;
    }

    .container form .inputBox label{
        display: block;
        padding-bottom: 5px;
    }

    .container form .inputBox input,.container form .inputBox select{
        width: 100%;
        padding: 10px;
        border-radius: 10px;
        border: 1px solid rgba(0,0,0,.3);
        color: #444;
    }

    .container form .flexbox{
        display: flex;
        gap: 15px;
    }

    .container form .flexbox .inputBox{
        flex: 1 1 150px;
    }

    .container form .submit-btn{
        width: 100%;
        background: black;
        margin-top: 20px;
        padding: 10px;
        font-size: 20px;
        color: #fff;
        cursor: pointer;
        transition: .2s linear;
    }


    .error {
      width  : 100%;
      padding: 0;
    
      font-size: 80%;
      color: white;
      background-color: #900;
      border-radius: 0 0 5px 5px;
    
      box-sizing: border-box;
    }
    
    .error.active {
      padding: 0.3em;
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
        
        <%
        
        if(authentication != null){ %>
        
        <li class="nav-item"><a class="nav-link" href="Logout"><i class="fas fa-sign-out-alt"></i></a></li>
        <%}else{ %>
        <li class="nav-item"><a class="nav-link" href="login.jsp"><i class="fas fa-sign-in-alt"></i></a></li>
        <%} %>
      </ul>
     
    </div>
</nav>	
</div>
	<!-- Header-nav bar ends -->

<div class="container">
        <form action="cardValidation" autocomplete="off" method="post">
            <div class="inputBox">
                <label>Name on card</label> 
                <input type="text" id="cardname" name="cardname" required minlength="3">
                <span class="error" aria-live="polite"></span>
            </div>

            <div class="inputBox">
                <label>Card number</label> 
                <input type="text" id="cardnumber" name="cardnumber" required  minlength="16" >
                <span class="error" aria-live="polite"></span>
            </div>


            <div class="flexbox">
                <div class="inputBox">
                    <label>Expire Month</label> 
                    <select name="" id="" class="month-input" required>
                        <option value="month" selected disabled>Month</option>
                        <option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>

                    </select>
                </div>

                <div class="inputBox">
                    <label>Expire year</label>
                    <select name="" id="" class="year-input" required>
                      <option value="year" selected disabled>Year</option>
                      <option value="2022">2022</option>
                      <option value="2023">2023</option>
                      <option value="2024">2024</option>
                      <option value="2025">2025</option>
                      <option value="2026">2026</option>
                      <option value="2027">2027</option>
                      <option value="2028">2028</option>
                      <option value="2029">2029</option>
                      <option value="2030">2030</option>
                      <option value="2031">2031</option>
                      <option value="2032">2032</option>
                      <option value="2033">2033</option>
                      <option value="2034">2034</option>
                      <option value="2035">2035</option>
                  </select>
                </div>
    
                <div class="inputBox">
                    <label>CVV</label> 
                    <input type="text" class="cvv-input" id="cvv" name="cvv" required minlength="3">
                    <span class="error" aria-live="polite"></span>
                </div>

            </div>
            <input type="submit" value="submit" id="button" class="submit-btn">

        </form>

    </div>




    <script>
        const form = document.getElementsByTagName('form')[0];
      
        const cardname = document.getElementById('cardname');
        const cardnumber = document.getElementById('cardnumber');
        const cvv = document.getElementById('cvv');
      
        const cardnameError = document.querySelector('#cardname + span.error');
        const cardnumberError = document.querySelector('#cardnumber+ span.error');
        const cvvError = document.querySelector('#cvv+ span.error');
      
        

        cardname.addEventListener('input',function(event){
          if(cardname.validity.valid){
            cardnameError.textContent = '';
            cardnameError.className = 'error';
          }else{
            showError();
          }
        });
      
        cardnumber.addEventListener('input',function(event){
          if(cardnumber.validity.valid){
            cardnumberError.textContent = '';
            cardnumberError.className='error';
          }else{
            showError();
          }
        });
      
        
      
        cvv.addEventListener('input',function(event){
          if(cvv.validity.valid){
            cvvError.textContent='';
            cvvError.className = 'error';
          }else{
            showError();
          }
        });
      
      form.addEventListener('submit',function(event){
        if(!cardname.validity.valid){
          showError();
          event.preventDefault();
        }
      
        if(!cardnumber.validity.valid){
          showError();
          event.preventDefault();
        }
      
        
      
        if(!cvv.validity.valid){
          showError();
          event.preventDefault();
        }
      });
      
      function showError(){

      var letters = /^[A-Za-z]+$/;
      var numbers = /^[0-9]+$/;


      if(cardname.validity.valueMissing){
        cardnameError.textContent = 'you need to fill the card name';
      }else if(!(cardname.value.match(letters))){
        cardnameError.textContent = 'Card name should contains only alphabets';
      }else if(cardnumber.validity.valueMissing){
        cardnumberError.textContent = 'you need to fill the card number';
      }else if(!(cardnumber.value.match(numbers) || (cardnumber.length == 16))){
        cardnumberError.textContent = 'Card number should contains numerics only and it have 16 digits';
      }else if(cvv.validity.valueMissing){
        cvvError.textContent = 'you need to fill the cvv';
      }else if(!(cvv.value.match(numbers))){
        cvvError.textContent = 'CVV year should contains numerics only';
      }else if(!(cvv<100 || cvv>999)){
        cvvError.textContent = 'cvv should contains 3 digits like 123'
      }
      
      
      
       
      
      cardnameError.className = 'error active';
      cardnumberError.className = 'error active';
      cvvError.className = 'error active';
      }
      
      
      
      </script>


	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>