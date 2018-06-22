package fr.epita.quiz.web.services.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.RolesType;
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
@WebServlet(urlPatterns = "/modifyUser")
public class ModifyUser extends SpringServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsersDAO repository;

	public ModifyUser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			final Users user = new Users();
			user.setEmail(request.getParameter("email"));
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setEnabled(true);
			user.setUserRoleId(Integer.parseInt(request.getParameter("selection")));
			user.setRole(RolesType.valueOf(request.getParameter("role")));
			try {
				repository.create(user);
				response.sendRedirect("usersList.jsp");
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getParameter("delete") != null) {
			try {
				Users deleteUser = repository.getUsersById(Integer.parseInt(request.getParameter("selection")));
				repository.delete(deleteUser);
				response.sendRedirect("usersList.jsp");
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getParameter("modify") != null) {

			Users editUser = repository.getUsersById(Integer.parseInt(request.getParameter("selection")));
			request.getSession().setAttribute("Users", editUser);
			response.sendRedirect("updateUser.jsp");
		}
	}
}
