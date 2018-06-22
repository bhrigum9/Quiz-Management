<%@page import="fr.epita.quiz.datamodel.Users"%>
<%@page import="fr.epita.quiz.services.GenericORMDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<Users> usersList = (List<Users>)session.getAttribute("usersList");
   session.removeAttribute("usersList");
%>
<head>
<%
	Boolean auth = (Boolean) session.getAttribute("authenticated");
	if (auth == null || !auth) {
%>
<meta http-equiv="refresh" content="0; URL=index.html">
<%
	}
%>

<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
   <div class="container">
		<div>
			<div class="jumbotron">
				<div class="container">
					<h1 class="text-info" align="center">List of all Questions</h1>
					<a href="question.jsp">Add New Question</a>
					<a href="Search.jsp">Search</a>
				</div>
			</div>

	</div>
	<div class="container">
		<h3 class="text-info">Search Results</h3>

		<form class="form-horizontal"  method="post" action="modifyUser">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Email</th>
							<th>Role Type</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
					   <%
					   	if (usersList == null || usersList.isEmpty()){
					   %>
						  <tr>
						      <td colspan="7">No result</td>
						  </tr>
						   
					   <%
						   					   	} else{
						   					   			   for (Users user : usersList){
						   					   %>
						<tr>
							<td><input name="selection" type="radio" value="<%=user.getUserRoleId()%>"/></td>
							<%-- <td> <label for="id" name="id" id="id" value="<%=id.getId() %>"><%=id.getId() %></label></td> --%>
<%-- 							<td><%=id.getId() %></td>
 --%>							
  							<td><%=user.getUsername() %></td>
							<td><%=user.getEmail() %></td> 
							<td><%=user.getRole() %></td>
							<td><%=user.isEnabled() %></td>
						</tr>
                        <%} 
                        }%>

					</tbody>
				</table>
			</div>
			<div class="form-group">
				<div class=" col-sm-offset-2 col-sm-10 text-right">
					
					<button type="submit" class="btn btn-primary" value="Modify" name="modify">Modify</button>
					<button type="submit" class="btn btn-primary" value="Delete" name="delete">Delete</button>
				</div>
	</div>
	</form>
	</div>
</div>
</body>
</html>
