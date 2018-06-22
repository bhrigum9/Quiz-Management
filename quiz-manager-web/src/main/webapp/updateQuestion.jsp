<%@page import="fr.epita.quiz.datamodel.Question"%>
<%@page import="fr.epita.quiz.services.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Question addQuestion = (Question) session.getAttribute("addQuestion");
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
					   if (addQuestion == null){%>
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
					<h1 class="text-info">Update Question</h1>
					<a href="<%=request.getContextPath() %>/questionList">List of Questions</a>
					<a href="search.jsp">Search</a>
				</div>
			</div>			</div>
			<form role="form" method="post" action="modifyQuestion">
			   <div class="form-group row">
					<label for="id">id</label> 
					<input name="id" value="<%=addQuestion.getId()%>" type="text" class="form-control" id="id"
						placeholder="enter  id" readonly="readonly" />
				</div>
				<div class="form-group row">
					<label for="question" class="col-sm-2 col-form-label">Question Title</label> 
					<input name="question" value="<%=addQuestion.getQuestion()%>" type="text" class="form-control" id="question"
						placeholder="Enter your Question" />
				</div>
				<div class="form-group row">
					<label for="option1" class="col-sm-2 col-form-label">Option1</label> 
					<input name="option1" value="<%=addQuestion.getOption1()%>" type="text" class="form-control" id="option1"
						placeholder="Enter your Option" />
				</div>
				<div class="form-group row">
					<label for="option2" class="col-sm-2 col-form-label">Option2</label> 
					<input name="option2" value="<%=addQuestion.getOption2()%>" type="text" class="form-control" id="option2"
						placeholder="Enter your Option" />
				</div>
				<div class="form-group row">
					<label for="option3" class="col-sm-2 col-form-label">Option3</label> 
					<input name="option3" value="<%=addQuestion.getOption3()%>" type="text" class="form-control" id="option3"
						placeholder="Enter your Option" />
				</div>
				<div class="form-group row">
					<label for="option4" class="col-sm-2 col-form-label">Option4</label> 
					<input name="option4" value="<%=addQuestion.getOption4()%>" type="text" class="form-control" id="option4"
						placeholder="Enter your Option" />
				</div>
				
				
				 <div class="form-group row">
                    <label for="answer">Correct Answer:</label>
                    <select class="form-control" id="answer" name="answer" >
                        <option>Please Select Any Answer</option>
                        <option>Option 1</option>
                        <option>Option 2</option>
                        <option>Option 3</option>
                        <option>Option 4</option>
                    </select>                   
                </div>
				
					<button type="submit" class="btn btn-primary" value="Update" name="update">Update</button>

			</form>
		</div>
	
</body>
							
							
						
                        <%} 
                        %>
                       

</html>
