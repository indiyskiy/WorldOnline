package view.servlet;

import controller.phone.parser.MobileErrorParser;
import helper.FileHelper;
import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.constants.ServerConsts;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class ServletHelper {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ServletHelper.class);

    public static void sendForward(String url, HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = servlet.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
//        response.sendRedirect(url);
    }


    public static void sendRedirect(String url, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(url);
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

    public static ParsedRequest getParametersMap(HttpServletRequest request) throws FileUploadException {
        HashMap<String, String> textMap = new HashMap<>();
        HashMap<String, File> fileMap = new HashMap<>();
        ParsedRequest parsedRequest = new ParsedRequest(textMap, fileMap);
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> uploadItems = upload.parseRequest(request);
            for (FileItem item : uploadItems) {
                String fieldName = item.getFieldName();
                if (item.isFormField()) {
                    String value = item.getString();
                    loggerFactory.debug(fieldName + " - " + value);
                    textMap.put(fieldName, value);
                } else {
                    String filename = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    File file = new File(ServerConsts.tempFileStore + "/" + filename);
                    FileHelper.saveToFile(fileContent, file);
                    fileMap.put(fieldName, file);
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return parsedRequest;
    }

    public static void throwServletException(ArrayList<String> exceptions) throws ServletException {
        String ex = "";
        for (String e : exceptions) {
            ex += e + "\n";
        }
        throw new ServletException(ex);
    }

    public static String saveAllParamsToHiddenInputType(HttpServletRequest request) {
        String res = "";
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            if (!value.isEmpty()) {
                res += "<input type=\"hidden\" value=\"" + value + "\" name=\"" + name + "\">";
            }
        }
        return res;
    }
}
