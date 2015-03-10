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
 * Servlet implementation class LoadMoreProfilImagesServlet
 */
@WebServlet("/LoadMoreProfilImagesServlet")
public class LoadMoreProfilImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Inject
    ImageController iController;
    public LoadMoreProfilImagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String index = request.getParameter("index");
		String number = request.getParameter("number");
		String username = request.getParameter("username");
		List<Image> iList=iController.retrieveLastPublicImageByUsername(username, new Integer(index), new Integer(number));
		if(iList.size()==0) {
			response.sendError(400);
			return;
		}
		PrintWriter pr =response.getWriter();
		for(Image i : iList) {
			pr.write("<div class=\"row\"><div class=\"col-md-12\">");
			pr.write("<IMG alt=\""+i.getDescription()+"\" src=\""+ request.getContextPath() +"/uploadedImg/"+i.getOwner().getUsername()+"-"+i.getUploadedTime()+".jpg\" class=\"img-responsive\"/>");
			pr.write("<dl><dt>"+i.getName()+"</dt><dd>"+i.getDescription()+"</dd></dl></div></div>");
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
