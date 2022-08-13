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
<meta charset="UTF-8">
<title>Title Here</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    
    <h1>Change ${tableMaster.name }</h1>
    
    
    
    
    <form:form action="/updatingTable/${tableMaster.id}" method="post" modelAttribute="tableMaster">
    
    			<input  type="hidden" name="_method" value="put"/>
    			<input  type="hidden" name="user" value="${tableMaster.user.id}"/>
    			
			    <p>
			        <form:label path="name">Name</form:label>
			        <form:errors class="alert-danger"  path="name"/>
			        <form:input path="name"/>
			    </p>
			    
			    <p>
			        <form:label path="guests">Number of Guests</form:label>
			        <form:errors class="alert-danger" path="guests"/>
			        <form:input type="number" path="guests"/>
			    </p>
			    
			    <p>
			        <form:label path="notes">Notes</form:label>
			        <form:errors class="alert-danger" path="notes"/>     
			        <form:input type="textarea" path="notes"/>
			    </p>
   
			    <input type="submit" value="Submit"/>
			</form:form> 
			
			 <a class="btn btn-danger"  href="/delete/${tableMaster.id}">Delete</a>
			 <a class="btn btn-danger" href="/dashboard">Cancel</a>
    
    
        
    </div> <!-- End of Container -->
</body>
</html>