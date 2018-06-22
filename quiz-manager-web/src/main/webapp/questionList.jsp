<%@page import="fr.epita.quiz.datamodel.Question"%>
<%@page import="fr.epita.quiz.services.GenericORMDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<Question> questionsList = (List<Question>)session.getAttribute("questionsList");
   session.removeAttribute("questionsList");
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
					<a href="<%=request.getContextPath() %>/usersService">List of Users</a>
				</div>
			</div>

	</div>
	<div class="container">
		<h3 class="text-info">Search Results</h3>

		<form class="form-horizontal"  method="post" action="modifyQuestion">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Question</th>
							<th>Option1</th>
							<th>Option2</th>
							<th>Option3</th>
							<th>Option4</th>
							<th>Answer</th>
						</tr>
					</thead>
					<tbody>
					   <%
					   	if (questionsList == null || questionsList.isEmpty()){
					   %>
						  <tr>
						      <td colspan="7">No result</td>
						  </tr>
						   
					   <%
						   					   	} else{
						   					   			   for (Question id : questionsList){
						   					   %>
						<tr>
							<td><input name="selection" type="radio" value="<%=id.getId()%>"/></td>
							<%-- <td> <label for="id" name="id" id="id" value="<%=id.getId() %>"><%=id.getId() %></label></td> --%>
<%-- 							<td><%=id.getId() %></td>
 --%>							
  							<td><%=id.getQuestion() %></td>
							<td><%=id.getOption1() %></td> 
							<td><%=id.getOption2() %></td>
							<td><%=id.getOption3() %></td>
							<td><%=id.getOption4() %></td>
							<td><%=id.getAnswer() %></td>
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
					<button type="submit" class="btn btn-primary" value="DeleteAll" name="deleteAll">Delete All</button>
				</div>
	</div>
	</form>
	</div>
</div>
</body>
</html>
