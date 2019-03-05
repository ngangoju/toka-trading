package toka.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/");// Get image file.
			

			String file = request.getParameter("file");
			String OS = null;
			BufferedInputStream in =null;
			// String path="c:/some\\\\path/file.txt";
			if (OS == null) {
				OS = System.getProperty("os.name");
			}
			
			if (OS.startsWith("Windows")) {
				LOGGER.info("CLASS::ImageServlet.SERVELET CLASS WINDOWS :: IMGAE Path::" + path+FILELOCATION  + file);
			in = new BufferedInputStream(new FileInputStream(path+FILELOCATION  + file));
			}else {
				LOGGER.info("CLASS::ImageServlet.SERVELET CLASS UNIX :: IMGAE Path::" + path+FILELOCATIONUNIX  + file);
			in = new BufferedInputStream(new FileInputStream(path+FILELOCATIONUNIX  + file));
			}

			
			// Get image contents.
			byte[] bytes = new byte[in.available()];

			in.read(bytes);
			in.close();

			// Write image contents to response.
			response.getOutputStream().write(bytes);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
