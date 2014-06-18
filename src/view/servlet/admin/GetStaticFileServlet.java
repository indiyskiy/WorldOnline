package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.ServerConsts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class GetStaticFileServlet extends ProtectedServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String fileName = request.getPathInfo().substring(1);
        File file = new File(ServerConsts.staticFilesDirectory + fileName);
        if (!file.exists()) {
            throw new ServletException(fileName + " not exist");
        }
        String mimeType = getServletContext().getMimeType(file.getAbsolutePath());
        if (mimeType == null) {
            throw new ServletException(file + " " + "mimetype is null");
        }

        response.reset();
        response.setContentType(mimeType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(new FileInputStream(file));
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
}