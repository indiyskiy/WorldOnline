package view.servlet.admin;

import model.database.requests.ImageRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 23.12.13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class ImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        try {
            Long imageID = Long.valueOf(request.getPathInfo().substring(1)); // 123
//            String idString = request.getParameter("ImageID");
//            if (idString != null && !idString.isEmpty()) {
//                long imageID = Long.parseLong(idString);
                File image = ImageRequest.getImage(imageID);
            if(image==null){
               throw new ServletException(imageID+"");
            }
                String mimeType = getServletContext().getMimeType(image.getAbsolutePath());
                if (mimeType == null) {
//                    sc.log("Could not get MIME type of "+filename);
                    throw new ServletException(imageID+" "+"mimetype");
                }

                response.reset();
                response.setContentType(mimeType);
//                response.setContentType("image/"+fileType);
//                response.setHeader("Content-Type", getServletContext().getMimeType(image.getName()));
                response.setHeader("Content-Length", String.valueOf(image.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

                BufferedInputStream input = null;
                BufferedOutputStream output = null;

                try {
                    input = new BufferedInputStream(new FileInputStream(image));
                    output = new BufferedOutputStream(response.getOutputStream());
                    byte[] buffer = new byte[8192];
                    for (int length = 0; (length = input.read(buffer)) > 0;) {
                        output.write(buffer, 0, length);
                    }
//                    throw new ServletException(imageID+" "+"uray!");
                } finally {
                    if (output != null) try {
                        output.flush();
                        output.close();
                    } catch (IOException e1) {
                        throw new ServletException(e1);
                    }
                    if (input != null) try {
                        input.close();
                    } catch (IOException e2) {
                        throw new ServletException(e2);
                    }
                }

//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//              throw e;
//        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}