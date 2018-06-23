package fr.epita.quiz.web.services;

import java.io.IOException;

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
@WebServlet(urlPatterns = "/questionAction")
public class QuestionAction extends SpringServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(QuestionAction.class);

	@Autowired
	private AddQuestionDAO repository;

	public QuestionAction() {
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
				final Question addQuestion = prepareQuestion(request);
				repository.create(addQuestion);
				LOGGER.info("Question created Sucessfully");
				response.sendRedirect("questionList");
			} catch (Exception e) {
				LOGGER.error(e);
				LOGGER.info("Question creation Failed");
				e.printStackTrace();
			}
		} else if (request.getParameter("open") != null) {
			try {
				final Question addQuestion = prepareOpenQuestion(request);
				repository.create(addQuestion);
				LOGGER.info("Question created Sucessfully");
				response.sendRedirect("questionList");
			} catch (Exception e) {
				LOGGER.error(e);
				LOGGER.info("Question creation Failed");
				e.printStackTrace();
			}

		} else if (request.getParameter("assoc") != null) {
			LOGGER.info("THIS FUNTIONALITY NOT PROVIDED YET");
		}

	}

	/**
	 * @param request
	 * @return
	 */
	private Question prepareQuestion(HttpServletRequest request) {
		final Question addQuestion = new Question();
		addQuestion.setQuestion(request.getParameter("question"));
		addQuestion.setOption1(request.getParameter("option1"));
		addQuestion.setOption2(request.getParameter("option2"));
		addQuestion.setOption3(request.getParameter("option3"));
		addQuestion.setOption4(request.getParameter("option4"));
		String correctAnswer = request.getParameter("answer");
		addQuestion.setAnswer(request.getParameter(correctAnswer));
		addQuestion.setQuizName(request.getParameter("quizName"));
		addQuestion.setType(QuestionType.MCQ);
		return addQuestion;
	}

	/**
	 * @param request
	 * @return
	 */
	private Question prepareOpenQuestion(HttpServletRequest request) {
		final Question addQuestion = new Question();
		addQuestion.setQuestion(request.getParameter("question"));
		addQuestion.setQuizName(request.getParameter("quizName"));
		addQuestion.setOption1("N/A");
		addQuestion.setOption2("N/A");
		addQuestion.setOption3("N/A");
		addQuestion.setOption4("N/A");
		addQuestion.setType(QuestionType.OPEN);
		return addQuestion;
	}

}
