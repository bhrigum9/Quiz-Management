package fr.epita.quiz.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.AddQuestionDAO;
import fr.epita.quiz.web.services.messages.QuestionMessage;

@Repository
public class QuestionService {

	@Inject
	private AddQuestionDAO question;

	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON_VALUE })
	public List<QuestionMessage> findAllQuestions() {
		final List<Question> questions = question.search(new Question());
		final List<QuestionMessage> messages = new ArrayList<>();
		for (final Question question : questions) {
			final QuestionMessage message = new QuestionMessage();
			message.setTitle(question.getQuestion());
		}
		return messages;
	}

}