<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!-- c:out ; c:forEach ; c:if -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (like dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Table Master</title>
    
    <!-- CSS file connection -->
    <link href="CSS/loginReg.css" rel="stylesheet"/>
    
    <!-- Bootstrap -->
    <!--  <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    /> -->
  </head>
  <body>
    <div class="container">
      <!-- Beginning of Container -->
		<div class="card">
			<div class="inner-box" id="card">
			
			<!-- Front of card (Log in page) -->
				<div class="card-front">
					 <h2>Login</h2>
					 <div class="col-4 order-last">
		    			<form:form action="/login" method="post" modelAttribute="newLogin">
		        			<div class="form-group">
		            			<form:input path="email" class="input-box" placeholder="Email Address" />
		            			<form:errors path="email" class="text-danger" />
		        			</div>
		        			<div class="form-group">
		            			<form:password path="password" class="input-box" placeholder="Password"/>
		            			<form:errors path="password" class="text-danger" />
		        			</div>
		        			<button type="submit" value="Login" class="submit-btn">LogIn</button>
	    				</form:form>
	    				<button type="button" class="btn" onclick="openRegister()">I'm new here</button>
	    		</div>
				
				
				</div>
				
			<!-- Back of card (registration page) -->
				<div class="card-back">
					<h2>Register</h2>
					<form:form action="/register" method="post" modelAttribute="newUser">
		        		<div class="form-group">
		            		
		            		<form:input path="userName" class="input-box" placeholder="Full Name"/>
		            		<form:errors path="userName" class="text-danger" />
		        		</div>
		        		<div class="form-group">
		            		
		            		<form:input path="email" class="input-box" placeholder="Email Address"/>
		            		<form:errors path="email" class="text-danger" />
		        		</div>
		        		<div class="form-group">
		            		
		            		<form:password path="password" class="input-box" placeholder="Password"/>
		            		<form:errors path="password" class="text-danger" />
		        		</div>
		        		<div class="form-group">
		            		
		            		<form:password path="confirm" class="input-box" placeholder="Confirm Password"/>
		            		<form:errors path="confirm" class="text-danger" />
		        		</div>
		        		<button type="submit" value="Register" class="submit-btn">Register</button>
		    		</form:form>
					
					<button type="button" class="btn" onclick="openLogin()">I've an account</button>
				
				</div>
			</div>
		
		
		</div>		    
		    		   
</div>
    <!-- End of Container -->
    
    
    <!-- JavaScript to rotate the card -->
    <script>
    
    var card = document.getElementById("card");
    
    function openRegister(){
    	card.style.transform = "rotateY(-180deg)";
    }
    
    function openLogin(){
    	card.style.transform = "rotateY(0deg)";
    }
    
    
    
    
    </script>
    
    
    
    
  </body>
</html>