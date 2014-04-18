package view.servlet;

import controller.phone.parser.MobileErrorParser;
import model.constants.Component;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import org.apache.http.HttpResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 17.10.13
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class ServletHelper {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ServletHelper.class);

    public static void sendForward(String url, HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = servlet.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public static void setUTF8(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }

    public static String getAndSetAttribute(HttpServletRequest request, String name) {
        if (name == null) {
            return null;
        }
        String param = request.getParameter(name);
        if (param != null) {
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
            request.setAttribute(name, param);
        }
        return param;
    }

    public static void sendResponse(HttpServletResponse response, String responseString) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(responseString);
        out.flush();
        out.close();
    }

    public static void sendResponse(OutputStream out, String responseString) throws IOException {
        out.write(responseString.getBytes());
        out.flush();
        out.close();
    }


    public static void sendJson(HttpServletResponse response, String responseString) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        sendResponse(response, responseString);
    }


    public static void sendJson(OutputStream outputStream, String responseString) throws IOException {
        sendResponse(outputStream, responseString);
    }


    public static void sendError(Exception error, HttpServletRequest request, HttpServletResponse response, HttpServlet servlet, LoggerFactory loggerFactory) {
        try {
            if (request != null) {
                request.setAttribute("errorMessage", error.getMessage());
            }
            loggerFactory.error(error);
            if (request != null && response != null && servlet != null) {
                ServletHelper.sendForward("/error.jsp", servlet, request, response);
            }
        } catch (ServletException | IOException e) {
            loggerFactory.warning(e);
        }
    }

    public static void sendMobileError(LoggerFactory loggerFactory, Exception error, HttpServletResponse response) {
        if (loggerFactory != null) {
            loggerFactory.error(error);
        } else {
            ServletHelper.loggerFactory.warning(error);
        }
        if (error.getClass() == ParseRequestException.class) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        String errorJSON = new MobileErrorParser().parse(error);
        try {
            sendJson(response, errorJSON);
        } catch (Exception e) {
            ServletHelper.loggerFactory.error(e);
        }
    }
}
