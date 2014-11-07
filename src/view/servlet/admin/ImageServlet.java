package view.servlet.admin;

import model.constants.AdminRule;
import model.database.requests.ImageRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

public class ImageServlet extends ProtectedServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long imageID = Long.valueOf(request.getPathInfo().substring(1)); // 123

        File image = ImageRequest.getImageFile(imageID);
        if (image == null) {
            throw new ServletException(imageID + " is null");
        }
//        String mimeType = getServletContext().getMimeType(image.getAbsolutePath());
        String mimeType = Files.probeContentType(image.toPath());
        if (mimeType == null) {
            throw new ServletException(imageID + " " + "mimetype");
        }

        response.reset();
        response.setContentType(mimeType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(new FileInputStream(image));
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[8192];
            for (int length = 0; (length = input.read(buffer)) > 0; ) {
                output.write(buffer, 0, length);
            }
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
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.All;
    }

    @Override
    public String getTitle() {
        return null;
    }
}