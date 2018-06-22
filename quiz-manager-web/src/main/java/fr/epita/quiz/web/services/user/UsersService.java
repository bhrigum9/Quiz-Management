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

import fr.epita.quiz.datamodel.Users;
import fr.epita.quiz.services.UsersDAO;
import fr.epita.quiz.web.actions.SpringServlet;

/**
 * 
 * @author Bhrigu
 *
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/usersService")
public class UsersService extends SpringServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	UsersDAO repository;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if (request.getParameter("list") != null) {
		List<Users> usersList = (List<Users>) repository.searchUsers(new Users());
		request.getSession().setAttribute("usersList", usersList);
		response.sendRedirect("usersList.jsp");
	}
}
