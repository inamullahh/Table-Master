<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <!-- c:out ; c:forEach ; c:if -->
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Formatting (like dates) -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <!-- form:form -->
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!-- for rendering errors on PUT routes -->
        <%@ page isErrorPage="true" %>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8" />
            <title>Title Here</title>
            <!-- Bootstrap -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous" />
          </head>

          <body>
            <div class="container">
              <!-- Beginning of Container -->

              <h1>Welcome,<c:out value="${user_name}" /></h1>

              <br />
              <a class="btn btn-primary" href="/newTable">New Table</a>
              <a class="btn btn-danger" href="/logout">Log Out</a>
              <br />

              <h2>Your Tables</h2>

			
				

              <table class="table table-dark table-striped table-hover">
                <thead>
                  <tr>
                    <th class="align-middle">Guest Name</th>
                    <th class="align-middle"># Guests</th>
                    <th class="align-middle">Notes</th>
                    <th class="align-middle">Arrived at</th>
                    <th class="align-middle">Actions</th>
                  </tr>
                </thead>
              
                
                <tbody>
                  <c:forEach var="i" items="${allNames}">
                  	<c:choose>
						<c:when test="${user_id == i.user.id}">	
                    <tr>
                      <td>                      
                          <c:out value="${i.name}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.guests}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.notes}"></c:out>
                      </td>
                      <td>
                        <c:out value="${i.createdAt}"></c:out>
                      </td>
					  <td><a class="btn btn-danger"  href="/delete/${i.id}">Finished</a>
					  <a class="btn btn-secondary" href="/updateTable/${i.id}">Edit</a></td>
					   	
                    </tr>
						</c:when>
					</c:choose>
                  </c:forEach>
                  
                </tbody>
                	
              </table>
              
              	
              
              <a class="btn btn-primary" href="/allTables">See Other Tables</a>
            </div>
            <!-- End of Container -->
          </body>

          </html>