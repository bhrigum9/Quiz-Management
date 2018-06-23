package fr.epita.quiz.services;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.Question;

/**
 * 
 * @author Bhrigu
 *
 */
public class AddQuestionDAO extends GenericORMDao<Question> {
	@Inject
	@Named("questionQuery")
	String query;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.epita.quiz.services.GenericORMDao#getWhereClauseBuilder(java.lang.Object)
	 */
	@Override
	protected WhereClauseBuilder getWhereClauseBuilder(Question entity) {
		// TODO Auto-generated method stub
		final WhereClauseBuilder<Question> wcb = new WhereClauseBuilder<>();
		wcb.setQueryString(query);
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("type", entity.getType());
		wcb.setParameters(parameters);
		return wcb;
	}

}
