package fr.epita.quiz.web.services;

import java.io.IOException;

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
@WebServlet(urlPatterns = "/questionAction")
public class QuestionAction extends SpringServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AddQuestionDAO repository;

	public QuestionAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String question = request.getParameter("question");
		System.out.println(question);
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
		repository.create(addQuestion);
		response.sendRedirect("questionList");

	}

}
