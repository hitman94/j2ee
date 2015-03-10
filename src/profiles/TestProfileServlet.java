package profiles;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserController;

/**
 * Servlet implementation class TestProfileServlet
 */
@WebServlet("/TestProfileServlet")
public class TestProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UserController uController;

	public TestProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getPathInfo().substring(1);
		if (uController.checkUsername(username)) {
			response.sendRedirect("../index.jsp");
			return;
		} else {
			request.setAttribute("username", username);
			getServletConfig().getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);		
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
