package images;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;

/**
 * Servlet implementation class ImageDeleteServlet
 */
@WebServlet("/ImageDeleteServlet")
public class ImageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	ImageController iController;

	public ImageDeleteServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("imageId");
		Image i =iController.findImg(new Long(imageId));
		if(i!=null) {
			iController.remove(i);
			String path=getServletContext().getRealPath("/uploadedImg/");
			File f = new File(path+"/"
					+ ((User)request.getSession().getAttribute("user")).getUsername()+"-"
					+ i.getUploadedTime().toString() + ".jpg");
			f.delete();
			response.setStatus(200);
			return;
		}
		response.sendError(400);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
