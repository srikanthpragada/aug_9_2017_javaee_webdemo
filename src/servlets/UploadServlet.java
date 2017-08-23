package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		Part p1 = request.getPart("file");
		InputStream is = p1.getInputStream();
		String path = getServletContext().getRealPath("picture.gif");
		System.out.println(path);

		FileOutputStream os = new FileOutputStream(path);

		int ch = is.read();
		while (ch != -1) {
			os.write(ch);
			ch = is.read();
		}
		os.close();
		
		pw.println("Uploaded Successfully!");
		
		

	}

}
