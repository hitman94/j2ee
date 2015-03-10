package images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TIFF;
import org.apache.tika.parser.image.ImageMetadataExtractor;
import org.xml.sax.SAXException;

import user.User;

/**
 * Servlet implementation class ImageUploadServlet
 */
@WebServlet("/ImageUploadServlet")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Inject
	ImageController iController;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageUploadServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/");
		Part p = request.getPart("imageLocation");
		String name = request.getParameter("nameImage");
		String description = request.getParameter("imageDesc");
		boolean isPublic =  (request.getParameter("isPublic")==null)?false:true;
		User user = (User)request.getSession().getAttribute("user");
		Long uploadedTime = System.currentTimeMillis();
		if(name.isEmpty()) {
			response.setStatus(400);
			response.getWriter().write("<html><head></head><body><div id= \"answer\">non</div><div id= \"error\">Le nom de l'image est vide</div></body></html>");
			return;
		}
		if (p.getContentType().equals("image/jpeg") &&  user != null) {
			File imgDir = new File(path+"/uploadedImg/");
			if(!imgDir.exists()) {
				if(imgDir.mkdir()) {
					path = getServletContext().getRealPath("/uploadedImg/");
				}
			} else {
				path = getServletContext().getRealPath("/uploadedImg/");
			}
			InputStream in = null;
			File f = null;
			FileOutputStream fout = null;
			try {
				in = p.getInputStream();

				f = new File(path+"/"
						+ user.getUsername()+"-"
						+ uploadedTime + ".jpg");
				fout = new FileOutputStream(f);
				int a;
				while ((a = in.read()) != -1) {
					fout.write(a);
				}
			} finally {

				if (fout != null)
					fout.close();

			}
			Metadata data = new Metadata();
			ImageMetadataExtractor extractor = new ImageMetadataExtractor(data);
			
			Long idCreated;
			try {
				extractor.parseJpeg(f);		
				idCreated=iController.createImage(name, description, new Double(data.get(TIFF.IMAGE_WIDTH)),
							new Double(data.get(TIFF.IMAGE_LENGTH)), 
							isPublic,
							uploadedTime,user);
			} catch (SAXException | TikaException e) {
				response.setStatus(400);
				response.getWriter().write("<html><head></head><body><div id= \"answer\">non</div><div id= \"error\">Votre image n'est pas au format JPG</div></body></html>");
				return;
			}
			response.setStatus(200);
			response.getWriter().write(
					"<html><head></head><body><div id= \"answer\">ok</div> <div id= \"idImg\">"+idCreated+"</div> <div id= \"upTime\">"+uploadedTime+"</div> <div id=\"username\">"+user.getUsername()+"</div> </body></html>");
			return;
		}

		response.setStatus(400);
		response.getWriter().write("<html><head></head><body><div id= \"answer\">non</div><div id= \"error\">Erreur lors de l'envoi de l'image</div></body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
