package fr.epita.quiz.web.services.user;

import java.io.IOException;

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
@WebServlet(urlPatterns = "/examResult")
public class ExamResult extends SpringServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	AddQuestionDAO repository;

	public ExamResult() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("unused")
		// List<Question> questions = new ArrayList<Question>();
		String[] question = request.getParameterValues("question");
		int rightAnswer = 0;
		for (int i = 0; i < question.length; i++) {
			if (request.getParameter("quesNum[" + i + "]") != null
					&& request.getParameter("option[" + i + "]") != null) {
				int questionId = Integer.parseInt(request.getParameter("quesNum[" + i + "]"));
				String optionMarked = request.getParameter("option[" + i + "]");
				Question questionGet = (Question) repository.getById(questionId);
				if (questionGet != null) {
					int comp = optionMarked.compareTo(questionGet.getAnswer());
					if (comp == 0) {
						rightAnswer++;
					}
				}
			}
		}
		int wrongAnswers = ((question.length) - rightAnswer);
		request.getSession().setAttribute("wrongAnswers", wrongAnswers);
		request.getSession().setAttribute("totalQuestions", question.length);
		request.getSession().setAttribute("rightAnswer", rightAnswer);

		response.sendRedirect("papaerResult.jsp");
	}
}
