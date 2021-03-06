package fr.epita.quiz.web.services.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.AddQuestionDAO;
import fr.epita.quiz.web.actions.SpringServlet;

/**
 * 
 * @author Bhrigu
 *
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/examServices")
public class ExamServices extends SpringServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ExamServices.class);

	@Autowired
	AddQuestionDAO repository;

	public ExamServices() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("mcq") != null) {
			try {
				final Question question = new Question();
				question.setType(QuestionType.MCQ);
				List<Question> ques = repository.getQuizName(question);
				request.getSession().setAttribute("ques", ques);
				LOGGER.info("Redirected Sucessfully");
				response.sendRedirect("selectQuizName.jsp");
			} catch (Exception e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		} else if (request.getParameter("quizName") != null) {
			try {
				final Question question = new Question();
				question.setQuizName(request.getParameter("selection"));
				List<Question> ques = repository.getQuestions(question);
				request.getSession().setAttribute("ques", ques);
				LOGGER.info("Redirected Sucessfully");
				response.sendRedirect("populateExam.jsp");
			} catch (Exception e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		}
	}
}
