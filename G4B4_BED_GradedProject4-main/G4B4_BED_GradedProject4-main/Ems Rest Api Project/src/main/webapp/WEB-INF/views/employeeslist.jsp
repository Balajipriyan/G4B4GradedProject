<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Employee Management System</title>
</head>

<body>

	<div class="container">

		<h3>Employee Lists </h3>
		<hr> 
        
        <c:url var="searchUrl" value="/employees/search" />

        <form action="${searchUrl}" class="form-inline">
        
        <c:url var="addUrl" value="/employees/showFormForAdd" />
       

          <!-- Add a button -->
          <a href="${addUrl}" class="btn btn-primary btn-sm mb-3"> Add employee </a> <input type="search" name="firstName"
          placeholder="firstName" class="form-control-sm ml-5 mr-2 mb-3" /> 
         

        <button type="submit" class="btn btn-success btn-sm mb-3">Search</button>
          



        <c:url var="logoutUrl" value="/logout" />

        <a href="${logoutUrl}" class="btn btn-primary btn-sm mb-3 mx-auto"> Logout </a>

      </form>
           

		<table class="table table-bordered table-striped">
			<thead class="thead-dark">
				<tr>
                    <th>Id</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email</th>
				    <th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${employees}" var="Employee">
					<tr>
                        <td><c:out value="${Employee.id}" /></td>
						<td><c:out value="${Employee.firstName}" /></td>
						<td><c:out value="${Employee.lastName}" /></td>
						<td><c:out value="${Employee.email}" /></td>
						
                        <td>
                            <!-- Add "update" button/link -->
          
                            <c:url var="updateUrl" value="/employees/showFormForUpdate?employeeId=${Employee.id}" />
          
                            <a href="${updateUrl}" class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
          
                            <c:url var="deleteUrl" value="/employees/delete?employeeId=${Employee.id}" />
          
                            <a href="${deleteUrl}" class="btn btn-danger btn-sm"
                              onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">
                              Delete </a>
          
                          </td>
          

					</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>

</body>
</html>
