package images;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeVisibilityServlet
 */
@WebServlet("/ChangeVisibilityServlet")
public class ChangeVisibilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	ImageController iController;
    public ChangeVisibilityServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("imageId");
		String isPublic = request.getParameter("visibility");
		
		Image i =iController.findImg(new Long(imageId));
		if(isPublic.equals("false")) {
			i.setPublic(false);
			iController.update(i);
		}
		else {
			i.setPublic(true);
			iController.update(i);
		}
		response.setStatus(200);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
