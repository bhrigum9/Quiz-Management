package fr.epita.quiz.web.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.AddQuestionDAO;
import fr.epita.quiz.web.actions.SpringServlet;

@Service
@Transactional
@WebServlet(urlPatterns = "/questionList")
public class QuestionList extends SpringServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AddQuestionDAO repository;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Question> questionsList = (List<Question>) repository.searchAll(new Question());
		request.getSession().setAttribute("questionsList", questionsList);
		response.sendRedirect("questionList.jsp");
	}

}
