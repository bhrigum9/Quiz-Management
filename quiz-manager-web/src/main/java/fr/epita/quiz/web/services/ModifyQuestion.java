package fr.epita.quiz.web.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.AddQuestionDAO;
import fr.epita.quiz.web.actions.SpringServlet;

@Service
@Transactional
@WebServlet(urlPatterns = "/modifyQuestion")
public class ModifyQuestion extends SpringServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AddQuestionDAO repository;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// LOGGER.info("value of ID" + id);
		if (request.getParameter("selection") != null) {
			if (request.getParameter("modify") != null) {

				Question editQuestion = (Question) repository
						.getById(Integer.parseInt(request.getParameter("selection")));
				request.getSession().setAttribute("addQuestion", editQuestion);

				response.sendRedirect("updateQuestion.jsp");

			} else if (request.getParameter("delete") != null) {
				Question deleteQuestion = (Question) repository
						.getById(Integer.parseInt(request.getParameter("selection")));
				try {
					repository.delete(deleteQuestion);
					response.sendRedirect(request.getContextPath() + "/questionList");
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (request.getParameter("update") != null) {

			final Question updateQuestion = new Question();
			updateQuestion.setQuestion(request.getParameter("question"));
			updateQuestion.setOption1(request.getParameter("option1"));
			updateQuestion.setOption2(request.getParameter("option2"));
			updateQuestion.setOption3(request.getParameter("option3"));
			updateQuestion.setOption4(request.getParameter("option4"));
			String correctAnswer = request.getParameter("answer");
			updateQuestion.setAnswer(request.getParameter(correctAnswer));
			updateQuestion.setId(Integer.parseInt(request.getParameter("id")));

			try {
				repository.create(updateQuestion);
				response.sendRedirect(request.getContextPath() + "/questionList");

			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (request.getParameter("deleteAll") != null) {

			final List<Question> deleteAllQuestions = (List<Question>) repository.searchAll(new Question());
			try {
				repository.deleteAll(deleteAllQuestions);
				response.sendRedirect(request.getContextPath() + "/questionAction");

			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/questionList");
		}

	}

}
