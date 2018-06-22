<%@page import="fr.epita.quiz.datamodel.Users"%>
<%@page import="fr.epita.quiz.services.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
 Users user = (Users) session.getAttribute("Users");
%>
<head>
<%
	Boolean auth = (Boolean) session.getAttribute("authenticated");
	if (auth == null || !auth) {
%>

<meta http-equiv="refresh" content="0; URL=index.jsp">
<%
    }
%>

<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<%
					   if (user == null){%>
<tr>
	<td colspan="4">No result</td>
</tr>

<% } else{
					    %>
<body>
	<div class="container">
		<div>
			<div class="jumbotron">
				<div class="container">
					<h1 align="center" class="text-info">Update User</h1>
					<div><a href="<%=request.getContextPath() %>/questionList">List ofQuestions</a> </div>
					<div align="right">
				<a href="adminLogin.html">Logout</a>
			</div>
						<div align="left"><a href="<%=request.getContextPath() %>/usersService">List of Users</a></div>
							
				</div>
			</div>
		</div>
		<form role="form" method="post" action="modifyUser">
			<div class="form-group row">
				 <input name="id"
					value="<%=user.getUserRoleId()%>" type="hidden" class="form-control"
					id="id" placeholder="enter  id" readonly="readonly" />
			</div>
			<div class="form-group row">
				<label for="question" class="col-sm-2 col-form-label">Username
					</label> <input name="question" value="<%=user.getUsername()%>"
					type="text" class="form-control" id="question"
					placeholder="Enter unique username" />
			</div>
			<div class="form-group row">
				<label for="email" class="col-sm-2 col-form-label">Email</label>
				<input name="email" value="<%=user.getEmail()%>" type="email"
					class="form-control" id="email" placeholder="Enter your Email" />
			</div>
			<div class="form-group row">
				<label for="role" class="col-sm-2 col-form-label">Role</label>
				<input name="role" value="<%=user.getRole()%>" type="text"
					class="form-control" id="role" placeholder="Enter your Role" />
			</div>
		
			<div class="form-group row">
				<label for="password" class="col-sm-2 col-form-label">Password</label>
				<input name="password" value="<%=user.getPassword()%>" type="password"
					class="form-control" id="password" placeholder="Enter your Password" />
			</div>
		
			<button type="submit" style="margin-center: 30px" class="btn btn-primary" value="Update"
				name="update">Update</button>

		</form>
	</div>

</body>



<%} 
                        %>


</html>
