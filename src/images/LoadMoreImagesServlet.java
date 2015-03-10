package images;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadMoreImagesServlet
 */
@WebServlet("/LoadMoreImagesServlet")
public class LoadMoreImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Inject
    ImageController iController;
    
    public LoadMoreImagesServlet() {
        super();
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String index = request.getParameter("index");
		String number = request.getParameter("number");
		String size = request.getParameter("size");
		String imgName = request.getParameter("name");
		log(imgName);
		log(size);
		List<Image> iList=iController.getPublicImageFrom(new Integer(index), new Integer(number), size, imgName);
		if(iList.size()==0) {
			response.sendError(400);
			return;
		}
		PrintWriter pr =response.getWriter();
		for(Image i : iList) {
			pr.write("<div class=\"col-md-4\">");
			pr.write("<IMG alt=\""+i.getDescription()+"\" src=\"uploadedImg/"+i.getOwner().getUsername()+"-"+i.getUploadedTime()+".jpg\" class=\"img-responsive\"/>");
			pr.write("<p>"+i.getName()+"</p>");
			pr.write("<p><a class=\"btn btn-default\" href=\"profil/"+i.getOwner().getUsername()+"\" role=\"button\">Voir le profil de "+i.getOwner().getUsername()+"</a></p></div>");
		}
		response.setStatus(200);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
