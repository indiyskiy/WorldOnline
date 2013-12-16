package view.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 17.10.13
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class ServletHelper {
    public static void sendForward(String url, HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = servlet.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public static void setUTF8(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }
}
