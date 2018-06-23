package fr.epita.quiz.web.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.DataException;
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
@WebServlet(urlPatterns = "/modifyQuestion")
public class ModifyQuestion extends SpringServlet {
	private static final Logger LOGGER = LogManager.getLogger(ModifyQuestion.class);

	private static final long serialVersionUID = 1L;
	@Autowired
	private AddQuestionDAO repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("selection") != null) {
			if (request.getParameter("modify") != null) {
				Question editQuestion = (Question) repository
						.getById(Integer.parseInt(request.getParameter("selection")));
				request.getSession().setAttribute("addQuestion", editQuestion);
				LOGGER.info("Redirected to Update User Sucessfully");
				if (editQuestion.getType().equals(QuestionType.MCQ)) {
					response.sendRedirect("updateQuestion.jsp");
				} else if (editQuestion.getType().equals(QuestionType.OPEN)) {
					response.sendRedirect("updateOpenQuestion.jsp");
				}

			} else if (request.getParameter("delete") != null) {
				Question deleteQuestion = (Question) repository
						.getById(Integer.parseInt(request.getParameter("selection")));
				try {
					repository.delete(deleteQuestion);
					LOGGER.info("Question Deleted Sucessfully");
					response.sendRedirect(request.getContextPath() + "/questionList");
				} catch (DataException e) {
					LOGGER.error(e);
					e.printStackTrace();
				}
			}
		} else if (request.getParameter("update") != null) {

			final Question updateQuestion = prepareQuestion(request);

			updateQuestions(request, response, updateQuestion);
		} else if (request.getParameter("updateOpen") != null) {

			final Question updateQuestion = prepareOpenQuestion(request);

			updateQuestions(request, response, updateQuestion);
		}

		else if (request.getParameter("deleteAll") != null) {

			final List<Question> deleteAllQuestions = (List<Question>) repository.searchAll(new Question());
			try {
				repository.deleteAll(deleteAllQuestions);
				LOGGER.info("All Questions deleted Sucessfully");
				response.sendRedirect(request.getContextPath() + "/questionAction");

			} catch (DataException e) {
				LOGGER.error(e);
				e.printStackTrace();
			}

		} else {
			LOGGER.info("Something went Wrong!!!");
			response.sendRedirect(request.getContextPath() + "/questionList");
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param updateQuestion
	 * @throws IOException
	 */
	private void updateQuestions(HttpServletRequest request, HttpServletResponse response,
			final Question updateQuestion) throws IOException {
		try {
			repository.create(updateQuestion);
			LOGGER.info("Question Updated Sucessfully");
			response.sendRedirect(request.getContextPath() + "/questionList");
		} catch (DataException e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 */
	private Question prepareQuestion(HttpServletRequest request) throws NumberFormatException {
		final Question updateQuestion = new Question();
		updateQuestion.setQuestion(request.getParameter("question"));
		updateQuestion.setOption1(request.getParameter("option1"));
		updateQuestion.setOption2(request.getParameter("option2"));
		updateQuestion.setOption3(request.getParameter("option3"));
		updateQuestion.setOption4(request.getParameter("option4"));
		updateQuestion.setQuizName(request.getParameter("quizName"));
		updateQuestion.setType(QuestionType.MCQ);
		updateQuestion.setAnswer(request.getParameter("answer"));
		updateQuestion.setId(Integer.parseInt(request.getParameter("id")));
		return updateQuestion;
	}

	private Question prepareOpenQuestion(HttpServletRequest request) throws NumberFormatException {
		final Question updateQuestion = new Question();
		updateQuestion.setQuestion(request.getParameter("question"));
		updateQuestion.setOption1("N/A");
		updateQuestion.setOption2("N/A");
		updateQuestion.setOption3("N/A");
		updateQuestion.setOption4("N/A");
		updateQuestion.setQuizName(request.getParameter("quizName"));
		updateQuestion.setType(QuestionType.OPEN);
		updateQuestion.setAnswer("");
		updateQuestion.setId(Integer.parseInt(request.getParameter("id")));
		return updateQuestion;
	}

}
