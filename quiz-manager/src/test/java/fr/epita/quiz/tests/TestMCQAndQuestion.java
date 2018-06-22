/**
 * Ce fichier est la propriété de Thomas BROUSSARD
 * Code application :
 * Composant :
 */
package fr.epita.quiz.tests;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.AddQuestionDAO;

/**
 * <h3>Description</h3>
 * <p>
 * This class allows to ...
 * </p>
 *
 * <h3>Usage</h3>
 * <p>
 * This class should be used as follows:
 * 
 * <pre>
 * <code>${type_name} instance = new ${type_name}();</code>
 * </pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 *         ${tags}
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestMCQAndQuestion {

	@Inject
	AddQuestionDAO questDAO;

	@Inject
	SessionFactory factory;

	// @Test
	public void testSave() {
		final Session session = factory.openSession();
		final Transaction tx = session.beginTransaction();
		final Question question = new Question();
		question.setQuestion("How to configure Hibernate?");
		question.setType(QuestionType.MCQ);

		questDAO.create(question);

		final Question choice = new Question();

		questDAO.create(choice);
		tx.commit();

	}

	// @Test
	public void testGetQuizType() {
		final Question question = new Question();
		question.setType(QuestionType.MCQ);
		List<Question> stri = questDAO.getQuizName(question);
	}

	@Test
	public void testGetQuestionsByQuizType() {
		final Question question = new Question();
		question.setQuizName("test");
		List<Question> stri = questDAO.getQuestions(question);
		System.out.println(stri.size());
	}

}
