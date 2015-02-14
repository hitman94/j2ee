package user;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConnectionUserServlet
 */
@WebServlet("/ConnectionUserServlet")
public class ConnectionUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Inject
	private UserController uController;
	
    public ConnectionUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setStatus(200);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Long id = uController.connexion(username, password);
		if( id != -1){
			response.setStatus(200);
			HttpSession session = request.getSession();
			session.setAttribute("name", username);
			session.setAttribute("id", id);
			return;
		}
		response.setStatus(400);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
