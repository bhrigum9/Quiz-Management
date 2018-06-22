package fr.epita.quiz.web.services.user;

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
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.AddQuestionDAO;
import fr.epita.quiz.web.actions.SpringServlet;

@Service
@Transactional
@WebServlet(urlPatterns = "/examServices")
public class ExamServices extends SpringServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	AddQuestionDAO repository;

	public ExamServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("mcq") != null) {
			final Question question = new Question();
			question.setType(QuestionType.MCQ);
			List<Question> ques = repository.getQuizName(question);
			request.getSession().setAttribute("ques", ques);
			response.sendRedirect("selectQuizName.jsp");
		} else if (request.getParameter("quizName") != null) {
			final Question question = new Question();
			question.setQuizName(request.getParameter("selection"));
			List<Question> ques = repository.getQuestions(question);
			request.getSession().setAttribute("ques", ques);
			response.sendRedirect("populateExam.jsp");
		}
	}
}
